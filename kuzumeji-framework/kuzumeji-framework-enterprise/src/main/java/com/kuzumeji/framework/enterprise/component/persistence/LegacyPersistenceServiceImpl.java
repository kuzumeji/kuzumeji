// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Collection;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
/**
 * 型付けデータ永続化サービス
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>{@link TypedPersistenceService 型付けデータ永続化サービスI/F} が実装されること。</li>
 * <li>{@link PersistenceServiceImpl データ永続化サービス} が継承されること。</li>
 * <li>{@link #LegacyPersistenceServiceImpl(EntityManager) コンストラクタ} でインスタンス化すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public class LegacyPersistenceServiceImpl extends AbstractPersistenceService implements
    LegacyPersistenceService {
    /**
     * コンストラクタ
     * @param manager {@link javax.persistence.EntityManager エンティティマネージャ}
     */
    public LegacyPersistenceServiceImpl(final EntityManager manager) {
        super(manager);
    }
    /** {@inheritDoc} */
    @Override
    public final void persist(final Object entity) {
        getManager().persist(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T merge(final T entity) {
        return getManager().merge(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final void remove(final Object entity) {
        getManager().remove(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T find(final Class<T> entityClass, final Object id) {
        return getManager().find(entityClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T find(final Class<T> entityClass, final Object id,
        final Map<String, Object> properties) {
        return getManager().find(entityClass, id, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T find(final Class<T> entityClass, final Object id, final LockModeType lockMode) {
        return getManager().find(entityClass, id, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T find(final Class<T> entityClass, final Object id,
        final LockModeType lockMode, final Map<String, Object> properties) {
        return getManager().find(entityClass, id, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T getReference(final Class<T> entityClass, final Object id) {
        return getManager().getReference(entityClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void lock(final T entity, final LockModeType lockMode) {
        getManager().lock(entity, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void lock(final T entity, final LockModeType lockMode,
        final Map<String, Object> properties) {
        getManager().lock(entity, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void refresh(final T entity) {
        getManager().refresh(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void refresh(final T entity, final Map<String, Object> properties) {
        getManager().refresh(entity, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void refresh(final T entity, final LockModeType lockMode) {
        getManager().refresh(entity, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void refresh(final T entity, final LockModeType lockMode,
        final Map<String, Object> properties) {
        getManager().refresh(entity, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> void detach(final T entity) {
        getManager().detach(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> boolean contains(final T entity) {
        return getManager().contains(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> LockModeType getLockMode(final T entity) {
        return getManager().getLockMode(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createQuery(final String jpql) {
        return getManager().createQuery(jpql);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> TypedQuery<T> createQuery(final CriteriaQuery<T> queryObject) {
        return getManager().createQuery(queryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> Query createQuery(final CriteriaUpdate<T> updateQueryObject) {
        return getManager().createQuery(updateQueryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> Query createQuery(final CriteriaDelete<T> deleteQueryObject) {
        return getManager().createQuery(deleteQueryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> TypedQuery<T> createQuery(final String jpql, final Class<T> resultClass) {
        return getManager().createQuery(jpql, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createNamedQuery(final String name) {
        return getManager().createNamedQuery(name);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> TypedQuery<T> createNamedQuery(final String name, final Class<T> resultClass) {
        return getManager().createNamedQuery(name, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createNativeQuery(final String sql) {
        return getManager().createNativeQuery(sql);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> Query createNativeQuery(final String sql, final Class<T> resultClass) {
        return getManager().createNativeQuery(sql, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createNativeQuery(final String sql, final String resultSetMapping) {
        return getManager().createNativeQuery(sql, resultSetMapping);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> T unwrap(final Class<T> entityClass) {
        return getManager().unwrap(entityClass);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> EntityGraph<T> createEntityGraph(final Class<T> rootType) {
        return getManager().createEntityGraph(rootType);
    }
    /** {@inheritDoc} */
    @Override
    public final <T> Collection<EntityGraph<? super T>> getEntityGraphs(final Class<T> entityClass) {
        return getManager().getEntityGraphs(entityClass);
    }
}
