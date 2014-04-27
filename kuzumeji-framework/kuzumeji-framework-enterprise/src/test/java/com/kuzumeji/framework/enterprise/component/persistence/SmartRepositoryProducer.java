// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kuzumeji.framework.enterprise.component.persistence.RepositoryAnnotations.SmartRepositoryPersistableTestee;
/**
 * @see SmartRepository
 * @see SmartRepositoryImpl
 * @author nilcy
 */
public class SmartRepositoryProducer {
    /** エンティティマネージャ */
    @Inject
    private EntityManager manager;
    /** コンストラクタ */
    public SmartRepositoryProducer() {
    }
    /**
     * 先進リポジトリの作成
     * @return 先進リポジトリ(テストエンティティ)
     */
    @Produces
    @SmartRepositoryPersistableTestee
    public SmartRepository<PersistableTestee, PersistableTestee> createPersistableTestee() {
        final SmartRepositoryListener<PersistableTestee, PersistableTestee> listener = new SmartRepositoryListener<PersistableTestee, PersistableTestee>() {
            @Override
            public CriteriaQuery<PersistableTestee> query(final CriteriaBuilder builder,
                final CriteriaQuery<PersistableTestee> query, final Root<PersistableTestee> root,
                final PersistableTestee filter) {
                query.select(root).where(builder.equal(root.get("code"), filter.getCode()));
                return query;
            }
        };
        return new SmartRepositoryImpl<>(PersistableTestee.class, manager, listener);
    }
}
