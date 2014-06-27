package net.java.cargotracker.application;
import net.java.cargotracker.domain.model.cargo.Cargo;
import net.java.cargotracker.domain.model.handling.HandlingEvent;
import net.java.cargotracker.interfaces.handling.HandlingEventRegistrationAttempt;
/**
 * アプリケーションイベントI/F
 * <dl>
 * <dt>表明保証
 * <dd>システムの他の部分へ発生イベントを通知する方法を提供すること。
 * <dt>特記など
 * <dd>同期または非同期で実装することができる。(ex:JMS)
 * </dl>
 * <p>
 * <p />
 * This interface provides a way to let other parts of the system know about events that have
 * occurred.
 * <p/>
 * It may be implemented synchronously or asynchronously, using for example JMS.
 */
public interface ApplicationEvents {
    /**
     * 貨物が処理された
     * @param event 処理イベント
     */
    void cargoWasHandled(HandlingEvent event);
    /**
     * 貨物の宛先を間違えた
     * @param cargo 貨物
     */
    void cargoWasMisdirected(Cargo cargo);
    /**
     * 貨物が到着した
     * @param cargo 貨物
     */
    void cargoHasArrived(Cargo cargo);
    /**
     * 処理イベント(登録の試み?)を受信した
     * @param attempt 処理イベント(登録の試み?)
     */
    void receivedHandlingEventRegistrationAttempt(HandlingEventRegistrationAttempt attempt);
}
