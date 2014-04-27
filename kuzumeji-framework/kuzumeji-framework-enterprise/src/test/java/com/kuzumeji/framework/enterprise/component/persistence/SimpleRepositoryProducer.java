// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import com.kuzumeji.framework.enterprise.component.persistence.RepositoryAnnotations.SimpleRepositoryPersistableTestee;
/**
 * @see SimpleRepository
 * @see SimpleRepositoryImpl
 * @author nilcy
 */
public class SimpleRepositoryProducer {
    /** エンティティマネージャ */
    @Inject
    private EntityManager manager;
    /** コンストラクタ */
    public SimpleRepositoryProducer() {
    }
    /**
     * リポジトリ(テストエンティティ)の作成
     * @return リポジトリ(テストエンティティ)
     */
    @Produces
    @SimpleRepositoryPersistableTestee
    public SimpleRepository<PersistableTestee> createPersistableTesteeUKcode() {
        // manager.setFlushMode(FlushModeType.AUTO);
        return new SimpleRepositoryImpl<>(PersistableTestee.class, manager);
    }
}
