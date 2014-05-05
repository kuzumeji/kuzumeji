// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain.auth;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.kuzumeji.framework.enterprise.component.persistence.PersistenceException;
import com.kuzumeji.framework.enterprise.component.persistence.SmartRepository;
import com.kuzumeji.framework.enterprise.component.persistence.SmartRepositoryImpl;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see SmartRepository
 * @see SmartRepositoryImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("javadoc")
public class CertificationRepositoryTest {
    @Inject
    private SmartRepository<Certification, CertificationFilter> Certification;
    @Inject
    private Logger log;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithJpa().addAsResource("config.properties")
            .addAsResource("error-messages.properties");
    }
    @Before
    public void before() throws PersistenceException {
        final Certification certification = new Certification("account#01",
            DigestUtils.sha256Hex("password#01"));
        final Collection<Permission> permissions = new ArrayList<>();
        permissions.add(new Permission("account#01", "user"));
        certification.setPermissions(permissions);
        Certification.save(certification);
        Certification.flush();
    }
    @Test
    public final void test() throws PersistenceException {
        final CertificationFilter filter = new CertificationFilter("account#01");
        final Certification entity = Certification.findOne(filter);
        log.debug("entity : {}", entity);
    }
}
