// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import com.kuzumeji.framework.testing.CoverageHelper;
/**
 * @see Hello
 * @author nilcy
 */
@SuppressWarnings("static-method")
public class HelloTest {
    /** @see Hello#sayHello(String) */
    @Test
    public void testSayHello() {
        CoverageHelper.privateConstructor(Hello.class);
        assertThat(Hello.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
