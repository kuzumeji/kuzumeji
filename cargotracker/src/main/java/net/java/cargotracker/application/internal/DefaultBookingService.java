package net.java.cargotracker.application.internal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import net.java.cargotracker.application.BookingService;
import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.cargo.CargoRepository;
import net.java.cargotracker.domain.model.cargo.Itinerary;
import net.java.cargotracker.domain.model.cargo.RouteSpecification;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.location.Location;
import net.java.cargotracker.domain.model.location.LocationRepository;
import net.java.cargotracker.domain.model.location.UnLocode;
import net.java.cargotracker.domain.service.RoutingService;
/**
 * 予約サービス
 * <dl>
 * <dt>表明保証
 * <dd>
 * <dt>特記など
 * <dd>
 * </dl>
 * @author nilcy
 */
@Stateless
public class DefaultBookingService implements BookingService {
    @Inject
    private CargoRepository cargoRepository;
    @Inject
    private LocationRepository locationRepository;
    @Inject
    private RoutingService routingService;
    // TODO See if the logger can be injected.
    private static final Logger logger = Logger.getLogger(DefaultBookingService.class.getName());
    @Override
    public TrackingId bookNewCargo(final UnLocode originUnLocode,
        final UnLocode destinationUnLocode, final Date arrivalDeadline) {
        final TrackingId trackingId = cargoRepository.nextTrackingId();
        final Location origin = locationRepository.find(originUnLocode);
        final Location destination = locationRepository.find(destinationUnLocode);
        final RouteSpecification routeSpecification = new RouteSpecification(origin, destination,
            arrivalDeadline);
        final Cargo cargo = new Cargo(trackingId, routeSpecification);
        cargoRepository.store(cargo);
        logger.log(Level.INFO, "Booked new cargo with tracking id {0}", cargo.getTrackingId()
            .getIdString());
        return cargo.getTrackingId();
    }
    @Override
    public List<Itinerary> requestPossibleRoutesForCargo(final TrackingId trackingId) {
        final Cargo cargo = cargoRepository.find(trackingId);
        if (cargo == null) {
            return Collections.emptyList();
        }
        return routingService.fetchRoutesForSpecification(cargo.getRouteSpecification());
    }
    @Override
    public void assignCargoToRoute(final Itinerary itinerary, final TrackingId trackingId) {
        final Cargo cargo = cargoRepository.find(trackingId);
        cargo.assignToRoute(itinerary);
        cargoRepository.store(cargo);
        logger.log(Level.INFO, "Assigned cargo {0} to new route", trackingId);
    }
    @Override
    public void changeDestination(final TrackingId trackingId, final UnLocode unLocode) {
        final Cargo cargo = cargoRepository.find(trackingId);
        final Location newDestination = locationRepository.find(unLocode);
        final RouteSpecification routeSpecification = new RouteSpecification(cargo.getOrigin(),
            newDestination, cargo.getRouteSpecification().getArrivalDeadline());
        cargo.specifyNewRoute(routeSpecification);
        cargoRepository.store(cargo);
        logger.log(Level.INFO, "Changed destination for cargo {0} to {1}", new Object[] {
            trackingId, routeSpecification.getDestination() });
    }
}
