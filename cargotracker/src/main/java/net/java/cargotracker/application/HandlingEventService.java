package net.java.cargotracker.application;
import java.util.Date;
import net.java.cargotracker.domain.model.cargo.TrackingId;
import net.java.cargotracker.domain.model.handling.CannotCreateHandlingEventException;
import net.java.cargotracker.domain.model.handling.HandlingEvent;
import net.java.cargotracker.domain.model.location.UnLocode;
import net.java.cargotracker.domain.model.voyage.VoyageNumber;
/**
 * イベント処理サービスI/F
 * <dl>
 * <dt>表明保証
 * <dd>
 * <dt>特記など
 * <dd>
 * </dl>
 * @author nilcy
 */
public interface HandlingEventService {
    /**
     * システム内のイベント処理を登録し、貨物が処理されたことを関係者に通知します。
     * <p/>
     * Registers a handling event in the system, and notifies interested parties that a cargo has
     * been handled.
     * @param completionTime 完了日時
     * @param trackingId 貨物の追跡ID
     * @param voyageNumber 航海番号
     * @param unLocode 国際ロケーションコード
     * @param type イベント処理タイプ
     * @throws CannotCreateHandlingEventException イベント処理の作成例外
     */
    void registerHandlingEvent(Date completionTime, TrackingId trackingId,
        VoyageNumber voyageNumber, UnLocode unLocode, HandlingEvent.Type type)
        throws CannotCreateHandlingEventException;
}
