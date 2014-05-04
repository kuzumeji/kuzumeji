// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.kuzumeji.framework.enterprise.component.persistence.SmartRepository;
import com.kuzumeji.framework.enterprise.component.persistence.SmartRepositoryImpl;
import com.kuzumeji.framework.enterprise.component.persistence.SmartRepositoryListener;
/**
 * 先進リポジトリ作成
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
     * JAASユーザ・リポジトリの作成
     * @return JAASユーザ・リポジトリ
     */
    @Produces
    public SmartRepository<JaasUser, JaasUserFilter> createPersistableTestee() {
        final SmartRepositoryListener<JaasUser, JaasUserFilter> listener = new SmartRepositoryListener<JaasUser, JaasUserFilter>() {
            @Override
            public CriteriaQuery<JaasUser> query(final CriteriaBuilder builder,
                final CriteriaQuery<JaasUser> query, final Root<JaasUser> root,
                final JaasUserFilter filter) {
                return query.select(root).where(
                    builder.equal(root.get("userName"), filter.getUserName()));
            }
        };
        return new SmartRepositoryImpl<>(JaasUser.class, manager, listener);
    }
}
