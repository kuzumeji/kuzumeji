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
    public void test() {
        CoverageHelper.privateConstructor(ArchiveFactory.class);
        assertThat(ArchiveFactory.createJar(), is(not(nullValue())));
        assertThat(ArchiveFactory.createJar("com.kuzumeji.framework.testing"), is(not(nullValue())));
        assertThat(ArchiveFactory.createJarWithJpa(), is(not(nullValue())));
    }
}
