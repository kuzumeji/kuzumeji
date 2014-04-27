// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.kuzumeji.framework.enterprise.component.persistence.RepositoryAnnotations.SmartRepositoryPersistableTestee;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see SmartRepository
 * @see SmartRepositoryImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("javadoc")
public class SmartRepositoryImplTest {
    @Inject
    @SmartRepositoryPersistableTestee
    private SmartRepository<PersistableTestee> testee;
    @Inject
    private Logger log;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithJpa().addAsResource("config.properties")
            .addAsResource("error-messages.properties");
    }
    @Before
    public void before() throws PersistenceException {
        testee.save(new PersistableTestee("code#01", "name#01"));
        testee.flush();
    }
    @Test
    public final void test() throws PersistenceException {
        final CriteriaBuilder b = testee.getBuilder();
        final CriteriaQuery<PersistableTestee> q = testee.query();
        final Root<PersistableTestee> r = testee.root();
        q.select(r);
        q.where(b.equal(r.get("code"), "code#01"));
        final PersistableTestee entity = testee.findOne(testee.query(q, 0, 100));
        log.debug("entity : {}", entity);
    }
}
