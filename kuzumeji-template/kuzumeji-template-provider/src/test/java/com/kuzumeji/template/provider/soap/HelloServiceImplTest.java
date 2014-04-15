// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.soap;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.xml.ws.WebServiceRef;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see HelloService
 * @see HelloServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@RunAsClient
public class HelloServiceImplTest {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/HelloService.wsdl")
    // @WebServiceRef(wsdlLocation = "http://localhost:8080/template-registry/HelloService?wsdl")
    private HelloService testee;
    /**
     * デプロイ
     * @return WAR
     */
    @Deployment
    public static WebArchive deploy() {
        return ArchiveFactory.createWarWithCdi();
    }
    @Test
    public void test() {
        assertThat(testee, is(not(nullValue())));
    }
}
