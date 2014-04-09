// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.cache.CacheManager;
import javax.cache.Caching;
import org.junit.Test;
/**
 * @see CacheManager
 * @author nilcy
 */
public class CacheTest {
    @Test
    public void test() {
        assertThat(Caching.getCacheManager(), is(not(nullValue())));
        assertThat(Caching.getCacheManager("application"), is(not(nullValue())));
        assertThat(Caching.getCacheManager("session"), is(not(nullValue())));
        assertThat(Caching.getCacheManager("conversation"), is(not(nullValue())));
        assertThat(Caching.getCacheManager("request"), is(not(nullValue())));
    }
}
