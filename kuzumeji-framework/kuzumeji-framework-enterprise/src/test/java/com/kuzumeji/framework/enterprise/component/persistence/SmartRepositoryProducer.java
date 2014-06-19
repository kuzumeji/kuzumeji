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
    public SmartRepository<Testee, TesteeFilter> createTestee() {
        final SmartRepositoryListener<Testee, TesteeFilter> listener = new SmartRepositoryListener<Testee, TesteeFilter>() {
            @Override
            public CriteriaQuery<Testee> query(final CriteriaBuilder builder,
                final CriteriaQuery<Testee> query, final Root<Testee> root,
                final TesteeFilter filter) {
                return query.select(root).where(builder.equal(root.get("code"), filter.getCode()));
            }
        };
        return new SmartRepositoryImpl<>(Testee.class, manager, listener);
    }
}
