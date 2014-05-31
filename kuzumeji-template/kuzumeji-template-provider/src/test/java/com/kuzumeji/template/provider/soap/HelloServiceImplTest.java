// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.soap;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.kuzumeji.framework.testing.AbstractSoapClientTest;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see HelloService
 * @see HelloServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@RunAsClient
public class HelloServiceImplTest extends AbstractSoapClientTest {
    /** 挨拶サービスI/F */
    private HelloService testee;
    /** コンストラクタ */
    public HelloServiceImplTest() {
        super("http://soap.provider.template.kuzumeji.com/", "HelloService");
    }
    /**
     * デプロイ
     * @return WAR
     */
    @Deployment
    public static WebArchive deploy() {
        return ArchiveFactory.createWarWithCdi();
    }
    /** テスト前処理 */
    @Before
    public void before() {
        testee = getPort(HelloService.class);
    }
    /** @see HelloService#sayHello(String) */
    @Test
    public void test() {
        assertThat(testee.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
