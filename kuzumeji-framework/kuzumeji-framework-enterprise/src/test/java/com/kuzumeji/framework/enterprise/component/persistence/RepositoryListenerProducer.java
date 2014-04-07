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
 * @see UniqueConstraintsListener
 * @author nilcy
 */
public class RepositoryListenerProducer {
    @PersistenceContext
    private EntityManager manager;
    /** コンストラクタ */
    public RepositoryListenerProducer() {
    }
    @Produces
    public UniqueConstraintsListener<PersistableTestee> createUniqueFilterFactory_PersistableTestee() {
        return new DefaultUniqueConstraintsListener<PersistableTestee>("PersistableTestee.findUK",
            "PersistableTestee_UK_code", "code");
        // return new UniqueConstraintsListener<PersistableTestee>() {
        // @Override
        // public String errorKey() {
        // return "PersistableTestee_UK_code";
        // }
        // @Override
        // public Object[] values(final PersistableTestee object) {
        // return new Object[] { object.getCode() };
        // }
        // @Override
        // public Map<String, Object> filter(final PersistableTestee object) {
        // final Map<String, Object> filter = new HashMap<>();
        // filter.put("code", object.getCode());
        // return filter;
        // }
        // };
    }
}
