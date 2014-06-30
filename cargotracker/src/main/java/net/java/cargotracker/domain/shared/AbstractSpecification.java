package net.java.cargotracker.domain.shared;
/**
 * {@code and},{@code or},{@code not}の標準実装がある合成仕様の抽象基本的な実装
 * <p/>
 * Abstract base implementation of composite {@link Specification} with default implementations for
 * {@code and}, {@code or} and {@code not}.
 * @param <T> 対象オブジェクト型
 */
public abstract class AbstractSpecification<T> implements Specification<T> {
    /** {@inheritDoc} */
    @Override
    public abstract boolean isSatisfiedBy(T t);
    /** {@inheritDoc} */
    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }
    /** {@inheritDoc} */
    @Override
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }
    /** {@inheritDoc} */
    @Override
    public Specification<T> not(final Specification<T> specification) {
        return new NotSpecification<>(specification);
    }
}
