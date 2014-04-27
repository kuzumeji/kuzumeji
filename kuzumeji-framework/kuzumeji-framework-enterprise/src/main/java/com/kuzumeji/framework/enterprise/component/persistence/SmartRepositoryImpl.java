// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kuzumeji.framework.enterprise.component.EnterpriseRuntimeException;
/**
 * 先進リポジトリ
 * @param <R> 基点エンティティ型
 * @param <F> 検索条件オブジェクト型
 * @author nilcy
 */
public class SmartRepositoryImpl<R extends Persistable, F> extends SimpleRepositoryImpl<R>
    implements SmartRepository<R, F> {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(SmartRepositoryImpl.class);
    /** ビルダー */
    private final CriteriaBuilder builder;
    private final SmartRepositoryListener<R, F> listener;
    /**
     * コンストラクタ
     * @param clazz エンティティクラス
     * @param manager エンティティマネージャ
     */
    public SmartRepositoryImpl(final Class<R> clazz, final EntityManager manager) {
        super(clazz, manager);
        builder = manager.getCriteriaBuilder();
        this.listener = new DefaultSmartRepositoryListener<R, F>();
    }
    /**
     * コンストラクタ
     * @param clazz エンティティクラス
     * @param manager エンティティマネージャ
     */
    public SmartRepositoryImpl(final Class<R> clazz, final EntityManager manager,
        final SmartRepositoryListener<R, F> listener) {
        super(clazz, manager);
        builder = manager.getCriteriaBuilder();
        this.listener = listener;
    }
    /**
     * {@link #builder} の取得
     * @return {@link #builder}
     */
    @Override
    public final CriteriaBuilder getBuilder() {
        return builder;
    }
    @Override
    public CriteriaQuery<R> query() {
        return query(getEntityClass());
    }
    @Override
    public <T> CriteriaQuery<T> query(final Class<T> entityClass) {
        return builder.createQuery(entityClass);
    }
    @Override
    public Root<R> root() {
        return query().from(getEntityClass());
    }
    @Override
    public <T> Root<T> root(final Class<T> entityClass) {
        return query(entityClass).from(entityClass);
    }
    @Override
    public <T> TypedQuery<T> query(final CriteriaQuery<T> query, final int... range) {
        final TypedQuery<T> q = getManager().createQuery(query);
        if (range.length > 0) {
            q.setFirstResult(range[0]);
        } else if (range.length > 1) {
            q.setMaxResults(range[1]);
        }
        return q;
    }
    public TypedQuery<R> query(final F filter) {
        return getManager().createQuery(listener.query(builder, query(), root(), filter));
    }
    @Override
    public <T> T findOne(final TypedQuery<T> query) throws PersistenceException {
        return query.getSingleResult();
    }
    /** {@inheritDoc} */
    @Override
    public <T> Collection<T> findMany(final TypedQuery<T> query) throws PersistenceException {
        return query.getResultList();
    }
    /**
     * 検索条件の作成
     * @param object 検索オブジェクト
     * @param fields 検索フィールド配列
     * @return 検索条件
     */
    protected Map<String, Object> filter(final R object, final String... fields) {
        final Map<String, Object> filter = new HashMap<>();
        for (final String field : fields) {
            try {
                filter.put(field, PropertyUtils.getSimpleProperty(object, field));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new EnterpriseRuntimeException(e.getLocalizedMessage());
            }
        }
        return filter;
    }
}
