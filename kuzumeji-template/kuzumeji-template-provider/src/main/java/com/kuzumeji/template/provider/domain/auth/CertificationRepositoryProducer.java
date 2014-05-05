// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain.auth;
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
 * 認証リポジトリ作成
 * @author nilcy
 */
public class CertificationRepositoryProducer {
    /** エンティティマネージャ */
    @Inject
    private EntityManager manager;
    /** コンストラクタ */
    public CertificationRepositoryProducer() {
    }
    /**
     * 先進リポジトリの作成
     * @return 先進リポジトリI/F
     */
    @Produces
    public SmartRepository<Certification, CertificationFilter> createSmartRepository() {
        final SmartRepositoryListener<Certification, CertificationFilter> listener = new SmartRepositoryListener<Certification, CertificationFilter>() {
            @Override
            public CriteriaQuery<Certification> query(final CriteriaBuilder builder,
                final CriteriaQuery<Certification> query, final Root<Certification> root,
                final CertificationFilter filter) {
                return query.select(root).where(
                    builder.equal(root.get("account"), filter.getAccount()));
            }
        };
        return new SmartRepositoryImpl<>(Certification.class, manager, listener);
    }
}
