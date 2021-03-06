package net.java.cargotracker.domain.shared;
/**
 * OR仕様オブジェクト
 * <dl>
 * <dt>表明保証
 * <dd>2つの別々な仕様オブジェクトからOR演算で新しい仕様オブジェクトを作成するときに使用すること。
 * <dt>特記など
 * <dd>
 * </dl>
 * <p/>
 * OR specification, used to create a new specification that is the OR of two other specifications.
 * @param <T> 対象オブジェクト型
 */
public class OrSpecification<T> extends AbstractSpecification<T> {
    /** 仕様オブジェクト#1 */
    private final Specification<T> spec1;
    /** 仕様オブジェクト#2 */
    private final Specification<T> spec2;
    /**
     * 2つの別々な仕様オブジェクトからOR演算で新しい仕様オブジェクトを作成します。
     * <p/>
     * Create a new OR specification based on two other spec.
     * @param spec1 仕様オブジェクト#1 (Specification one.)
     * @param spec2 仕様オブジェクト#2 (Specification two.)
     */
    public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }
    /**
     * {@inheritDoc} 仕様オブジェクト#1と仕様オブジェクト#2のOR演算が満たしているかどうかを確認します。
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t);
    }
}
