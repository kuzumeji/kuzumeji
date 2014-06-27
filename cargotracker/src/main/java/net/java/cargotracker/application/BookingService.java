package net.java.cargotracker.application;
import java.util.Date;
import java.util.List;
import net.java.cargotracker.domain.model.cargo.Itinerary;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.location.UnLocode;
/**
 * 予約サービス
 * <p/>
 * Cargo booking service.
 */
public interface BookingService {
    /**
     * まだ経路を指定されていない貨物を、追跡システムへ登録します。
     * <p/>
     * Registers a new cargo in the tracking system, not yet routed.
     * @param origin 出発地
     * @param destination 到着地
     * @param arrivalDeadline 到着期限
     * @return 貨物の追跡ID
     */
    TrackingId bookNewCargo(UnLocode origin, UnLocode destination, Date arrivalDeadline);
    /**
     * この貨物のための可能な経路を記述する配送予定リストを要求します。
     * <p/>
     * Requests a list of itineraries describing possible routes for this cargo.
     * @param trackingId 貨物の追跡ID (cargo tracking id)
     * @return この貨物のための可能な配送予定リスト (A list of possible itineraries for this cargo)
     */
    List<Itinerary> requestPossibleRoutesForCargo(TrackingId trackingId);
    /**
     * 経路へ貨物を割り当てます。
     * @param itinerary 配送予定
     * @param trackingId 貨物の追跡ID
     */
    void assignCargoToRoute(Itinerary itinerary, TrackingId trackingId);
    /**
     * 目的地を変更します。
     * @param trackingId 貨物の追跡ID
     * @param unLocode 国際ロケーションコード
     */
    void changeDestination(TrackingId trackingId, UnLocode unLocode);
}
