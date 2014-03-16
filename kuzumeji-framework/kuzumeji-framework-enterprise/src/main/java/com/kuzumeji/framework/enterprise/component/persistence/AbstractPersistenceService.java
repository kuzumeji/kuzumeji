// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
/**
 * データ永続化サービス
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>{@link PersistenceService データ永続化サービスI/F} が実装されること。</li>
 * <li>{@link #AbstractPersistenceService(EntityManager) コンストラクタ} でインスタンス化すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public abstract class AbstractPersistenceService implements PersistenceService {
    /** エンティティマネージャ */
    private final EntityManager manager;
    /**
     * コンストラクタ
     * @param manager {@link #manager}
     */
    public AbstractPersistenceService(final EntityManager manager) {
        assert manager != null;
        this.manager = manager;
    }
    /** {@inheritDoc} */
    @Override
    public final void flush() {
        manager.flush();
    }
    /** {@inheritDoc} */
    @Override
    public final void setFlushMode(final FlushModeType flushMode) {
        manager.setFlushMode(flushMode);
    }
    /** {@inheritDoc} */
    @Override
    public final FlushModeType getFlushMode() {
        return manager.getFlushMode();
    }
    /** {@inheritDoc} */
    @Override
    public final void clear() {
        manager.clear();
    }
    /** {@inheritDoc} */
    @Override
    public final void setProperty(final String name, final Object value) {
        manager.setProperty(name, value);
    }
    /** {@inheritDoc} */
    @Override
    public final Map<String, Object> getProperties() {
        return manager.getProperties();
    }
    /** {@inheritDoc} */
    @Override
    public final void joinTransaction() {
        manager.joinTransaction();
    }
    /** {@inheritDoc} */
    @Override
    public final boolean isJoinedToTransaction() {
        return manager.isJoinedToTransaction();
    }
    /** {@inheritDoc} */
    @Override
    public final Object getDelegate() {
        return manager.getDelegate();
    }
    /** {@inheritDoc} */
    @Override
    public final void close() {
        manager.close();
    }
    /** {@inheritDoc} */
    @Override
    public final boolean isOpen() {
        return manager.isOpen();
    }
    /** {@inheritDoc} */
    @Override
    public final EntityTransaction getTransaction() {
        return manager.getTransaction();
    }
    /** {@inheritDoc} */
    @Override
    public final EntityManagerFactory getEntityManagerFactory() {
        return manager.getEntityManagerFactory();
    }
    /** {@inheritDoc} */
    @Override
    public final CriteriaBuilder getCriteriaBuilder() {
        return manager.getCriteriaBuilder();
    }
    /** {@inheritDoc} */
    @Override
    public final Metamodel getMetamodel() {
        return manager.getMetamodel();
    }
    /** {@inheritDoc} */
    @Override
    public final EntityGraph<?> createEntityGraph(final String graphName) {
        return manager.createEntityGraph(graphName);
    }
    /** {@inheritDoc} */
    @Override
    public final EntityGraph<?> getEntityGraph(final String graphName) {
        return manager.getEntityGraph(graphName);
    }
    /** {@inheritDoc} */
    @Override
    public final StoredProcedureQuery createNamedStoredProcedureQuery(final String name) {
        return getManager().createNamedStoredProcedureQuery(name);
    }
    /** {@inheritDoc} */
    @Override
    public final StoredProcedureQuery createStoredProcedureQuery(final String procedureName) {
        return getManager().createStoredProcedureQuery(procedureName);
    }
    /** {@inheritDoc} */
    @Override
    public final StoredProcedureQuery createStoredProcedureQuery(final String procedureName,
        final Class<?>... resultClasses) {
        return getManager().createStoredProcedureQuery(procedureName, resultClasses);
    }
    /** {@inheritDoc} */
    @Override
    public final StoredProcedureQuery createStoredProcedureQuery(final String procedureName,
        final String... resultSetMappings) {
        return getManager().createStoredProcedureQuery(procedureName, resultSetMappings);
    }
    /** {@inheritDoc} */
    @Override
    public final EntityManager getManager() {
        return manager;
    }
}
