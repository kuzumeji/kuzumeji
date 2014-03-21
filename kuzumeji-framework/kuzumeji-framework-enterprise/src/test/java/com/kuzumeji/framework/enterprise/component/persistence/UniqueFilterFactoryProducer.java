// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * @see UniqueFilterFactory
 * @author nilcy
 */
public class UniqueFilterFactoryProducer {
    @PersistenceContext
    private EntityManager manager;
    /** コンストラクタ */
    public UniqueFilterFactoryProducer() {
    }
    @Produces
    public UniqueFilterFactory<PersistableTestee> createUniqueFilterFactory_PersistableTestee() {
        return new UniqueFilterFactory<PersistableTestee>() {
            @Override
            public Map<String, Object> create(final PersistableTestee object) {
                final Map<String, Object> filter = new HashMap<>();
                filter.put("code", object.getCode());
                return filter;
            }
            @Override
            public Object[] toArray(final PersistableTestee object) {
                return new Object[] { object.getCode() };
            }
        };
    }
}
