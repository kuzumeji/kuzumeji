// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.support.coding;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see Hello
 * @author nilcy
 */
@SuppressWarnings({ "static-method", "javadoc" })
public class HelloTest {
    @Test
    public void test() {
        assertThat(Hello.sayHello("nilcy"), is("こんにちは nilcy さん。"));
    }
}
