package net.java.cargotracker.domain.shared;
/**
 * ドメインオブジェクトのユーティリティ
 * <dl>
 * <dt>表明保証
 * <dd>
 * <dt>特記など
 * <dd>TODO CDIシングルトンにすべきか? (Make this a CDI singleton?)
 * </dl>
 * @author nilcy
 */
public class DomainObjectUtils {
    /**
     * nullセーフに変換します。
     * @param actual 実際値 (actual value)
     * @param safe nullセーフな値 (a null-safe value)
     * @param <T> 対象オブジェクト型 (type)
     * @return 実際値がnullでないとき実際値、nullなときnullセーフ値 (actual value, if it's not null, or safe value if
     *         the actual value
     *         is null.)
     */
    public static <T> T nullSafe(final T actual, final T safe) {
        return actual == null ? safe : actual;
    }
    // TODO wrappers for some of the commons-lang code:
    //
    // EqualsBuilder that uses sameIdentity/sameValue,
    // better validation (varargs etc)
    /**
     * インスタンス化を防ぐ。 (Prevent instantiation.)
     */
    private DomainObjectUtils() {
    }
}
