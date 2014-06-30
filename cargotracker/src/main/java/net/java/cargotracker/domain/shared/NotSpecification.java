package net.java.cargotracker.domain.shared;
/**
 * NOT仕様オブジェクト(デコレータ)
 * <dl>
 * <dt>表明保証
 * <dd>指定された仕様オブジェクトの逆転(NOT演算)で新しい仕様オブジェクトを作成するときに使用すること。
 * <dt>特記など
 * <dd>
 * </dl>
 * <p/>
 * NOT decorator, used to create a new specifcation that is the inverse (NOT) of the given spec.
 * @param <T> 対象オブジェクト型
 */
public class NotSpecification<T> extends AbstractSpecification<T> {
    /** 仕様オブジェクト#1 */
    private final Specification<T> spec1;
    /**
     * 別の仕様オブジェクトをもとに新しいNOT仕様オブジェクトを作成します。
     * <p/>
     * Create a new NOT specification based on another spec.
     * @param spec1 NOT演算に使用する仕様オブジェクト (Specification instance to not.)
     */
    public NotSpecification(final Specification<T> spec1) {
        this.spec1 = spec1;
    }
    /**
     * {@inheritDoc} 仕様オブジェクト#1のNOT演算が満たしているかどうかを確認します。
     */
    @Override
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
