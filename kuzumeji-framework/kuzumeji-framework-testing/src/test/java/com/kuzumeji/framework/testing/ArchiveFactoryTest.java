// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
/**
 * @see ArchiveFactory
 * @author nilcy
 */
public class ArchiveFactoryTest {
    /** 非公開コンストラクタ */
    @Test
    public void testConstructor() {
        CoverageHelper.privateConstructor(ArchiveFactory.class);
    }
    /**
     * @see ArchiveFactory#createJar(String...)
     */
    @Test
    public void testCreateJar() {
        JavaArchive jar = ArchiveFactory.createJar();
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJar("com.kuzumeji.framework.testing");
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJar("com.kuzumeji.framework.testing").addAsResource(
            "config.properties");
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJar("com.kuzumeji.framework.testing").addAsManifestResource(
            EmptyAsset.INSTANCE, "beans.xml");
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJar("com.kuzumeji.framework.testing")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "persistence.xml");
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJarWithCdi();
        assertThat(jar, is(not(nullValue())));
        jar = ArchiveFactory.createJarWithJpa();
        assertThat(jar, is(not(nullValue())));
    }
    /**
     * @see ArchiveFactory#createWar(String...)
     */
    @Test
    public void testCreateWar() {
        WebArchive war = ArchiveFactory.createWar();
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWar("com.kuzumeji.framework.testing");
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWar("com.kuzumeji.framework.testing").addAsResource(
            "config.properties");
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWar("com.kuzumeji.framework.testing").addAsWebInfResource(
            EmptyAsset.INSTANCE, "beans.xml");
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWar("com.kuzumeji.framework.testing")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "persistence.xml");
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWarWithCdi();
        assertThat(war, is(not(nullValue())));
        war = ArchiveFactory.createWarWithJpa();
        assertThat(war, is(not(nullValue())));
    }
}
