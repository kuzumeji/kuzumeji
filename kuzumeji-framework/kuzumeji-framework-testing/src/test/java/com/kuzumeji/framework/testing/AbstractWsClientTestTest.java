// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see AbstractWsClientTest
 * @author nilcy
 */
@SuppressWarnings("javadoc")
public class AbstractWsClientTestTest {
    private final AbstractWsClientTest testee = new AbstractWsClientTest() {
    };
    /** @see AbstractWsClientTest#getBase() */
    @Test
    public void test() {
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.getBase(), is(nullValue()));
    }
}
