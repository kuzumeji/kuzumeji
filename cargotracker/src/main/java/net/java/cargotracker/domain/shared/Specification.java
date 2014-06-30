package net.java.cargotracker.domain.shared;
/**
 * 仕様オブジェクトI/F
 * <dl>
 * <dt>表明保証
 * <dd>仕様オブジェクトを作成するには、基本クラス{@link AbstractSpecification}を拡張して、1つのメソッド
 * {@link #isSatisfiedBy(Object)} を実装すること。
 * <dt>特記など
 * <dd>
 * </dl>
 * Specification interface.
 * <p/>
 * Use {@link net.java.cargotracker.domain.shared.AbstractSpecification} as base for creating
 * specifications, and only the method {@link #isSatisfiedBy(Object)} must be implemented.
 * @param <T> 対象オブジェクト型
 */
public interface Specification<T> {
    /**
     * {@code t}が仕様を満たしているかどうかを確認します。
     * <p/>
     * Check if {@code t} is satisfied by the specification.
     * @param t 対象オブジェクト(Object to test.)
     * @return {@code t}が仕様を満たしているとき{@code true} ({@code true} if {@code t} satisfies the
     *         specification.)
     */
    boolean isSatisfiedBy(T t);
    /**
     * {@code this}仕様と追加仕様のAND演算で新しい仕様を作成します。
     * <p/>
     * Create a new specification that is the AND operation of {@code this} specification and
     * another specification.
     * @param specification AND演算する追加仕様 (Specification to AND.)
     * @return 新しい仕様 (A new specification.)
     */
    Specification<T> and(Specification<T> specification);
    /**
     * {@code this}仕様と追加仕様のOR演算で新しい仕様を作成します。
     * <p/>
     * Create a new specification that is the OR operation of {@code this} specification and another
     * specification.
     * @param specification OR演算する追加仕様 (Specification to AND.)
     * @return 新しい仕様 (A new specification.)
     */
    Specification<T> or(Specification<T> specification);
    /**
     * {@code this}仕様のNOT演算で新しい仕様を作成します。
     * <p/>
     * Create a new specification that is the NOT operation of {@code this} specification.
     * @param specification NOT演算する仕様? (Specification to AND.)
     * @return 新しい仕様 (A new specification.)
     */
    Specification<T> not(Specification<T> specification);
}
