// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.rest;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see VariableResource
 * @author nilcy
 */
@RunWith(Arquillian.class)
@RunAsClient
public class VariableResourceITest {
    private static final Logger LOG = LoggerFactory.getLogger(VariableResourceITest.class);
    @ArquillianResource
    private URL base;
    private Client client;
    private WebTarget root;
    @Deployment
    public static WebArchive deploy() {
        return ArchiveFactory.createWar(null, null);
    }
    @Before
    public void initClient() throws URISyntaxException {
        client = ClientBuilder.newClient();
        root = client.target(base.toURI() + "resources/variables/");
    }
    @Test
    public void test() {
        final Collection<Variable> variables = root.request(MediaType.APPLICATION_XML_TYPE).get(
            new GenericType<Collection<Variable>>() {
            });
        client.close();
        LOG.debug("variables={}", variables);
    }
}
