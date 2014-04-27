// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.kuzumeji.framework.enterprise.component.persistence.RepositoryAnnotations.StandardRepositoryPersistableTestee;
/**
 * @see StandardRepository
 * @see StandardRepositoryImpl
 * @author nilcy
 */
public class StandardRepositoryProducer {
    /** エンティティマネージャ */
    @Inject
    private EntityManager manager;
    /** コンストラクタ */
    public StandardRepositoryProducer() {
    }
    /**
     * リポジトリ(テストエンティティ)の作成
     * @return リポジトリ(テストエンティティ)
     */
    @Produces
    @StandardRepositoryPersistableTestee
    public StandardRepository<PersistableTestee> createPersistableTestee() {
        // manager.setFlushMode(FlushModeType.AUTO);
        return new StandardRepositoryImpl<>(PersistableTestee.class, manager);
    }
}
