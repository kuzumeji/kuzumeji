// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
    public SmartRepository<PersistableTestee> createPersistableTestee() {
        return new SmartRepositoryImpl<PersistableTestee>(PersistableTestee.class, manager);
    }
}
