// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @see StandardRepository
 * @see StandardRepositoryImpl
 * @author nilcy
 */
public class StandardRepositoryProducer {
    /** エンティティマネージャ */
    @PersistenceContext
    private EntityManager manager;
    /** コンストラクタ */
    public StandardRepositoryProducer() {
    }
    /**
     * リポジトリ(テストエンティティ)の作成
     * @return リポジトリ(テストエンティティ)
     */
    @Produces
    public StandardRepository<PersistableTestee> createPersistableTestee() {
        // manager.setFlushMode(FlushModeType.AUTO);
        return new StandardRepositoryImpl<PersistableTestee>(PersistableTestee.class, manager);
    }
}
