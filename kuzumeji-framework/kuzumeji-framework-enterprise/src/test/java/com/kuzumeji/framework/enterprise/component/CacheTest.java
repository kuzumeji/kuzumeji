// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.ehcache.CacheManager;
/**
 * @see CacheManager
 * @author nilcy
 */
public class CacheTest {
    private static final Logger LOG = LoggerFactory.getLogger(CacheTest.class);
    @Test
    public void test() {
        for (final String name : CacheManager.getInstance().getCacheNames()) {
            LOG.debug("name={}", name);
        }
    }
}
