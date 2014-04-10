// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see ArchiveFactory
 * @author nilcy
 */
public class ArchiveFactoryTest {
    @Test
    public void testConstructor() {
        CoverageHelper.privateConstructor(ArchiveFactory.class);
    }
    @Test
    public void testCreateJar() {
        assertThat(ArchiveFactory.createJar(null, null), is(not(nullValue())));
        assertThat(
            ArchiveFactory.createJar(new String[] { "com.kuzumeji.framework.testing" }, null),
            is(not(nullValue())));
        assertThat(ArchiveFactory.createJar(new String[] { "com.kuzumeji.framework.testing" },
            new String[] { "config.properties" }), is(not(nullValue())));
    }
    @Test
    public void testCreateJarWithJpa() {
        assertThat(ArchiveFactory.createJarWithJpa(null, null), is(not(nullValue())));
        assertThat(ArchiveFactory.createJarWithJpa(
            new String[] { "com.kuzumeji.framework.testing" }, null), is(not(nullValue())));
        assertThat(
            ArchiveFactory.createJarWithJpa(new String[] { "com.kuzumeji.framework.testing" },
                new String[] { "config.properties" }), is(not(nullValue())));
    }
}
