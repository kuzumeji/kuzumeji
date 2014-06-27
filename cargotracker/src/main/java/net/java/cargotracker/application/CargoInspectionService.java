package net.java.cargotracker.application;
import javax.validation.constraints.NotNull;
import net.java.cargotracker.domain.model.cargo.TrackingId;
/**
 * 貨物の検査サービス
 * <dl>
 * <dt>表明保証
 * <dd>
 * <dt>特記など
 * <dd>
 * </dl>
 * @author nilcy
 */
public interface CargoInspectionService {
    /**
     * 貨物を検査し、貨物が誤って誘導するか、最終目的地へ荷降ろしされている場合などに、関係者へ関連した通知メールを送信します。
     * <p/>
     * Inspect cargo and send relevant notifications to interested parties, for example if a cargo
     * has been misdirected, or unloaded at the final destination.
     * @param trackingId 貨物の追跡ID
     */
    public void inspectCargo(@NotNull(message = "Tracking ID is required") TrackingId trackingId);
}
