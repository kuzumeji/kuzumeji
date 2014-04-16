// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.net.URISyntaxException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.junit.Before;
import org.junit.Test;
import mockit.Deencapsulation;
import mockit.Mocked;
/**
 * @see AbstractRestClientTest
 * @author nilcy
 */
@SuppressWarnings("javadoc")
public class AbstractRestClientTestTest {
    private final AbstractRestClientTest testee = new AbstractRestClientTest("testee/") {
    };
    @Mocked
    private Client client;
    @Mocked
    private WebTarget root;
    @Before
    public void before() throws Exception {
        Deencapsulation.setField(testee, client);
        Deencapsulation.setField(testee, root);
    }
    @Test
    public void test() throws URISyntaxException {
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.client(), is(not(nullValue())));
        assertThat(testee.root(), is(not(nullValue())));
    }
}
