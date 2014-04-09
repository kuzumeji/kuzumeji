// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.cache.Cache;
import org.junit.Test;
/**
 * @see CacheHelper
 * @author nilcy
 */
public class CacheHelperTest {
    @Test
    public void test() {
        final CacheHelper<String, String> helper = new CacheHelper<>();
        assertThat(helper, is(not(nullValue())));
        Cache<String, String> var = helper.getCache("var");
        assertThat(var, is(nullValue()));
        var = helper.createCache("var");
        assertThat(var, is(not(nullValue())));
        var.put("foo", "foo#01");
        assertThat(var.get("foo"), is("foo#01"));
    }
}
