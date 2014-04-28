// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.kuzumeji.framework.enterprise.component.persistence.RepositoryAnnotations.StandardRepositoryPersistableTestee;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see StandardRepository
 * @see StandardRepositoryImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("javadoc")
@Ignore
public class StandardRepositoryImplTest {
    @Inject
    @StandardRepositoryPersistableTestee
    private StandardRepository<Testee> testee;
    @Inject
    private Logger log;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithJpa().addAsResource("config.properties")
            .addAsResource("error-messages.properties");
    }
    @Before
    public void before() throws PersistenceException {
        testee.save(new Testee("code#01", "name#01"));
        testee.flush();
    }
    @Test
    public final void test() throws PersistenceException {
        final Testee filter = new Testee("code#01", "name#01");
        final Testee entity = testee.findOne("PersistableTestee.findUK_code", filter,
            "code");
        log.debug("entity : {}", entity);
    }
}
