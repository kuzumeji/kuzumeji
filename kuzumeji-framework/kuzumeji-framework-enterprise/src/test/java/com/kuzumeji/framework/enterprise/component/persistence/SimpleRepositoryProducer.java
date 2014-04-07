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
 * @see SimpleRepository
 * @author nilcy
 */
public class SimpleRepositoryProducer {
    @PersistenceContext
    private EntityManager manager;
    /** コンストラクタ */
    public SimpleRepositoryProducer() {
    }
    @Produces
    public SimpleRepository<PersistableTestee> createPersistableTesteeUKcode() {
        return new SimpleRepository<PersistableTestee>(PersistableTestee.class, manager,
            new DefaultUniqueConstraintsListener<PersistableTestee>(
                "PersistableTestee.findUK_code", "PersistableTestee.UK_code", "code"),
            new DefaultUniqueConstraintsListener<PersistableTestee>(
                "PersistableTestee.findUK_name", "PersistableTestee.UK_name", "name"));
    }
}
