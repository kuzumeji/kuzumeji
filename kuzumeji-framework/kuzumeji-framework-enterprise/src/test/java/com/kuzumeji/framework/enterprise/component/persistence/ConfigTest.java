// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.DatabaseConfiguration;
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
 * @see Config
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
@SuppressWarnings("all")
public class ConfigTest {
    @Resource
    private DataSource dataSource;
    @Inject
    private Logger log;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithJpa();
    }
    @Test
    public final void test() throws PersistenceException {
        final Configuration testee = new DatabaseConfiguration(dataSource, "config", "name", "key",
            "value", "testee");
        assertThat(testee, is(not(nullValue())));
    }
}
