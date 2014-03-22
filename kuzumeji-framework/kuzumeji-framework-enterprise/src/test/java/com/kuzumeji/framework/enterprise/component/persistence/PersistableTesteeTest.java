// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.Collection;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("all")
public class PersistableTesteeTest {
    @Inject
    private Logger log;
    @PersistenceContext
    private EntityManager manager;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJar_Jpa();
    }
    @Test
    public final void test() {
        assertThat(manager, is(not(nullValue())));
        final PersistableTestee entity = new PersistableTestee();
        entity.setCode("code#01");
        entity.setName("name#01");
        manager.persist(entity);
        final Collection<PersistableTestee> entities = manager.createQuery(
            "select pt from PersistableTestee pt").getResultList();
        assertThat(entities.size(), is(1));
        final PersistableTestee first = entities.iterator().next();
        assertThat(first.getCode(), is("code#01"));
        assertThat(first.getName(), is("name#01"));
    }
}