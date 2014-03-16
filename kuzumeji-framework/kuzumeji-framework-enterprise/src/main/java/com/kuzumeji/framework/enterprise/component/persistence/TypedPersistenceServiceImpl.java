// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.io.Serializable;
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
 * <li>{@link #TypedPersistenceServiceImpl(EntityManager, Class) コンストラクタ} でインスタンス化すること。</li>
 * </ol>
 * </dl>
 * @param <PE> 基点エンティティ型
 * @param <ID> 識別子オブジェクト型
 * @author nilcy
 */
public class TypedPersistenceServiceImpl<PE extends Persistable<ID>, ID extends Serializable>
    extends AbstractPersistenceService implements TypedPersistenceService<PE, ID> {
    /** 基点エンティティクラス */
    private final Class<PE> rootClass;
    /**
     * コンストラクタ
     * @param manager {@link javax.persistence.EntityManager エンティティマネージャ}
     * @param rootClass {@link #rootClass}
     */
    public TypedPersistenceServiceImpl(final EntityManager manager, final Class<PE> rootClass) {
        super(manager);
        assert rootClass != null;
        this.rootClass = rootClass;
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void persist(final SE entity) {
        getManager().persist(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE merge(final SE entity) {
        return getManager().merge(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void remove(final SE entity) {
        getManager().remove(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final PE find(final ID id) {
        return find(rootClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE find(final Class<SE> entityClass, final ID id) {
        return getManager().find(entityClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final PE find(final ID id, final Map<String, Object> properties) {
        return find(rootClass, id, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE find(final Class<SE> entityClass, final ID id,
        final Map<String, Object> properties) {
        return getManager().find(entityClass, id, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final PE find(final ID id, final LockModeType lockMode) {
        return find(rootClass, id, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE find(final Class<SE> entityClass, final ID id,
        final LockModeType lockMode) {
        return getManager().find(entityClass, id, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final PE find(final ID id, final LockModeType lockMode,
        final Map<String, Object> properties) {
        return find(rootClass, id, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE find(final Class<SE> entityClass, final ID id,
        final LockModeType lockMode, final Map<String, Object> properties) {
        return getManager().find(entityClass, id, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final PE getReference(final ID id) {
        return getReference(rootClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE getReference(final Class<SE> entityClass, final ID id) {
        return getManager().getReference(entityClass, id);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void lock(final SE entity, final LockModeType lockMode) {
        getManager().lock(entity, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void lock(final SE entity, final LockModeType lockMode,
        final Map<String, Object> properties) {
        getManager().lock(entity, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void refresh(final SE entity) {
        getManager().refresh(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void refresh(final SE entity, final Map<String, Object> properties) {
        getManager().refresh(entity, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void refresh(final SE entity, final LockModeType lockMode) {
        getManager().refresh(entity, lockMode);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void refresh(final SE entity, final LockModeType lockMode,
        final Map<String, Object> properties) {
        getManager().refresh(entity, lockMode, properties);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> void detach(final SE entity) {
        getManager().detach(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> boolean contains(final SE entity) {
        return getManager().contains(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> LockModeType getLockMode(final SE entity) {
        return getManager().getLockMode(entity);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createQuery(final String jpql) {
        return getManager().createQuery(jpql);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> TypedQuery<SE> createQuery(final CriteriaQuery<SE> queryObject) {
        return getManager().createQuery(queryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> Query createQuery(final CriteriaUpdate<SE> updateQueryObject) {
        return getManager().createQuery(updateQueryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> Query createQuery(final CriteriaDelete<SE> deleteQueryObject) {
        return getManager().createQuery(deleteQueryObject);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> TypedQuery<SE> createQuery(final String jpql,
        final Class<SE> resultClass) {
        return getManager().createQuery(jpql, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final TypedQuery<PE> createNamedQuery(final String name) {
        return createNamedQuery(name, rootClass);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> TypedQuery<SE> createNamedQuery(final String name,
        final Class<SE> resultClass) {
        return getManager().createNamedQuery(name, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createNativeQuery(final String sql) {
        return getManager().createNativeQuery(sql);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> Query createNativeQuery(final String sql,
        final Class<SE> resultClass) {
        return getManager().createNativeQuery(sql, resultClass);
    }
    /** {@inheritDoc} */
    @Override
    public final Query createNativeQuery(final String sql, final String resultSetMapping) {
        return getManager().createNativeQuery(sql, resultSetMapping);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> SE unwrap(final Class<SE> entityClass) {
        return getManager().unwrap(entityClass);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> EntityGraph<SE> createEntityGraph(final Class<SE> rootType) {
        return getManager().createEntityGraph(rootType);
    }
    /** {@inheritDoc} */
    @Override
    public final <SE extends PE> Collection<EntityGraph<? super SE>> getEntityGraphs(
        final Class<SE> entityClass) {
        return getManager().getEntityGraphs(entityClass);
    }
}
