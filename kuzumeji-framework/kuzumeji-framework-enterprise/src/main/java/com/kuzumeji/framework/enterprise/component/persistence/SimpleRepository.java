// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 簡単リポジトリ
 * @param <P> エンティティ型
 * @author nilcy
 */
public class SimpleRepository<P extends Persistable> implements Repository<P> {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(SimpleRepository.class);
    /** エンティティクラス */
    private final Class<P> clazz;
    /** エンティティマネージャ */
    private final EntityManager manager;
    /** 一意キー制約リスナー */
    private final UniqueConstraintsListener<P>[] uniqueListeners;
    // /**
    // * コンストラクタ
    // * @param clazz {@link #clazz エンティティクラス}
    // * @param manager {@link #manager エンティティマネージャ}
    // */
    // public SimpleRepository(final Class<P> clazz, final EntityManager manager) {
    // this.clazz = clazz;
    // this.manager = manager;
    // uniqueListeners = null;
    // }
    /**
     * コンストラクタ
     * @param clazz {@link #clazz エンティティクラス}
     * @param manager {@link #manager エンティティマネージャ}
     * @param uniqueListeners {@link #uniqueListeners 一意キー制約リスナー}
     */
    @SafeVarargs
    public SimpleRepository(final Class<P> clazz, final EntityManager manager,
        final UniqueConstraintsListener<P>... uniqueListeners) {
        this.clazz = clazz;
        this.manager = manager;
        this.uniqueListeners = uniqueListeners;
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> S save(final S entity) throws PersistenceException {
        beforeSave(entity);
        if (!entity.isPersisted()) {
            manager.persist(entity);
        } else {
            manager.merge(entity);
        }
        manager.flush();
        return entity;
    }
    /**
     * エンティティの保存前処理
     * @param entity エンティティ
     * @throws PersistenceException 保存の失敗
     */
    private void beforeSave(final P entity) throws PersistenceException {
        if (uniqueListeners != null) {
            final Map<String, Object[]> messageMap = new LinkedHashMap<>();
            for (final UniqueConstraintsListener<P> listener : uniqueListeners) {
                final Map<String, Object> filter = listener.filter(entity);
                try {
                    final P other = findOne(listener.queryName(), filter);
                    if (!entity.isPersisted() || !other.identity().equals(entity.identity())) {
                        messageMap.put(listener.errorKey(), listener.values(entity));
                    }
                } catch (final NoResultException e) {
                }
            }
            if (!messageMap.isEmpty()) {
                final PersistenceException ex = new PersistenceException(messageMap);
                LOG.warn(ex.getApplicationMessage());
                throw ex;
            }
        }
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> Collection<S> save(final Iterable<S> entities) throws PersistenceException {
        final List<S> results = new ArrayList<>();
        for (final S entity : entities) {
            results.add(save(entity));
        }
        return results;
    }
    /** {@inheritDoc} */
    @Override
    public P find(final Object id) {
        return manager.find(clazz, id);
    }
    /** {@inheritDoc} */
    @Override
    public P findOne(final String name, final Map<String, Object> filter) {
        final String queryName = !name.startsWith(clazz.getSimpleName()) ? clazz.getSimpleName()
            + "." + name : name;
        final TypedQuery<P> query = manager.createNamedQuery(queryName, clazz);
        for (final Entry<String, Object> entry : filter.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getSingleResult();
    }
    /** {@inheritDoc} */
    @Override
    public Collection<P> findMany(final Object filter) {
        return null;
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> void delete(final S entity) throws PersistenceException {
        manager.remove(manager.merge(entity));
        manager.flush();
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> void delete(final Iterable<S> entities) throws PersistenceException {
        for (final S entity : entities) {
            manager.remove(manager.merge(entity));
        }
    }
    /** {@inheritDoc} */
    @Override
    public void flush() throws PersistenceException {
        manager.flush();
    }
}
