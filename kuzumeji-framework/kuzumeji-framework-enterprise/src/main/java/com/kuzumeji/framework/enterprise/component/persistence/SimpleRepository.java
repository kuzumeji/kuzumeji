// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 * 簡単リポジトリ
 * @param <P> エンティティ型
 * @author nilcy
 */
public class SimpleRepository<P extends Persistable> implements Repository<P> {
    /** エンティティクラス */
    private final Class<P> clazz;
    /** エンティティマネージャ */
    private final EntityManager manager;
    /**
     * コンストラクタ
     * @param clazz {@link #clazz エンティティクラス}
     * @param manager {@link #manager エンティティマネージャ}
     */
    public SimpleRepository(final Class<P> clazz, final EntityManager manager) {
        this.clazz = clazz;
        this.manager = manager;
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> S save(final S entity) {
        if (!entity.isPersisted()) {
            manager.persist(entity);
        } else {
            manager.merge(entity);
        }
        manager.flush();
        return entity;
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> Collection<S> save(final Iterable<S> entities) {
        final List<S> results = new ArrayList<>();
        for (final S entity : entities) {
            if (!entity.isPersisted()) {
                manager.persist(entity);
            } else {
                manager.merge(entity);
            }
            results.add(entity);
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
    public <S extends P> void delete(final S entity) {
        manager.remove(manager.merge(entity));
        manager.flush();
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> void delete(final Iterable<S> entities) {
        for (final S entity : entities) {
            manager.remove(manager.merge(entity));
        }
    }
    /** {@inheritDoc} */
    @Override
    public void flush() {
        manager.flush();
    }
}
