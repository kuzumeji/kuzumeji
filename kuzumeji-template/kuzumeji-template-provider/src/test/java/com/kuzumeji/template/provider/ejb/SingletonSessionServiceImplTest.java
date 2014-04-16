// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.ejb;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.kuzumeji.framework.testing.ArchiveFactory;
import com.kuzumeji.template.provider.ejb.SingletonSessionServiceImpl;
import com.kuzumeji.template.registry.ejb.SingletonSessionService;
import com.kuzumeji.template.registry.ejb.SingletonSessionServiceRemote;
/**
 * @see SingletonSessionService
 * @see SingletonSessionServiceRemote
 * @see SingletonSessionServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@Transactional(value = TransactionMode.ROLLBACK)
public class SingletonSessionServiceImplTest {
    /** @see SingletonSessionService */
    @EJB
    private SingletonSessionService testee;
    /** @see SingletonSessionServiceRemote */
    @EJB
    private SingletonSessionServiceRemote testeeRemote;
    /**
     * デプロイ
     * @return JAR
     */
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithCdi();
    }
    /** @see SingletonSessionService#sayHello(String) */
    @Test
    public final void test() {
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
    /** @see SingletonSessionServiceRemote#sayHello(String) */
    @Test
    public final void testRemote() {
        assertThat(testeeRemote, is(not(nullValue())));
        assertThat(testeeRemote.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
