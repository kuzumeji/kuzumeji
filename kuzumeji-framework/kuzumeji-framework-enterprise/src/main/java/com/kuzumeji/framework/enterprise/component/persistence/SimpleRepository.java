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
    /** 重複キー */
    private static final String DUPLICATE = "DUPLICATE";
    /** エンティティクラス */
    private final Class<P> clazz;
    /** エンティティマネージャ */
    private final EntityManager manager;
    /** UK制約条件 */
    private final UniqueFilterFactory<P> uniqueFilterFactory;
    /** ロガー */
    private final Logger log = LoggerFactory.getLogger(SimpleRepository.class);
    /**
     * コンストラクタ
     * @param clazz {@link #clazz エンティティクラス}
     * @param manager {@link #manager エンティティマネージャ}
     */
    public SimpleRepository(final Class<P> clazz, final EntityManager manager) {
        this.clazz = clazz;
        this.manager = manager;
        uniqueFilterFactory = null;
    }
    /**
     * コンストラクタ
     * @param clazz {@link #clazz エンティティクラス}
     * @param manager {@link #manager エンティティマネージャ}
     * @param uniqueFilterFactory {@link #uniqueFilterFactory UK制約条件}
     */
    public SimpleRepository(final Class<P> clazz, final EntityManager manager,
        final UniqueFilterFactory<P> uniqueFilterFactory) {
        this.clazz = clazz;
        this.manager = manager;
        this.uniqueFilterFactory = uniqueFilterFactory;
    }
    /** {@inheritDoc} */
    @Override
    public P save(final P entity) throws PersistenceException {
        P other = null;
        log.debug("uniqueFilterFactory : {}", uniqueFilterFactory);
        if (uniqueFilterFactory != null) {
            final Map<String, Object> filter = uniqueFilterFactory.create(entity);
            log.debug("filter : {}", filter);
            try {
                other = findOne("findUK", filter);
            } catch (final NoResultException e) {
            }
        }
        final Object[] array = uniqueFilterFactory.toArray(entity);
        log.debug("array : {}", array);
        if (!entity.isPersisted()) {
            if (other != null) {
                throw new PersistenceException(DUPLICATE, array);
            }
            manager.persist(entity);
        } else {
            log.debug("other : {}", other);
            log.debug("entity : {}", entity);
            if (other != null) {
                log.debug("other-id={}, entity-id={}", other.identity(), entity.identity());
            }
            if ((other != null) && !other.identity().equals(entity.identity())) {
                throw new PersistenceException(DUPLICATE, array);
            }
            manager.merge(entity);
        }
        manager.flush();
        return entity;
    }
    /** {@inheritDoc} */
    @Override
    public <S extends P> Collection<S> save(final Iterable<S> entities) throws PersistenceException {
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
