// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
/**
 * アーカイブのファクトリー
 * @author nilcy
 */
public final class ArchiveFactory {
    /** ベースパッケージ */
    private static final String BASE_PACKAGE = "com.kuzumeji";
    /** CDI定義ソース */
    private static final String CDI_SOURCE = "META-INF/beans.xml";
    /** CDI定義ターゲット */
    private static final String CDI_TARGET = "beans.xml";
    /** JPA定義ソース */
    private static final String JPA_SOURCE = "META-INF/persistence.xml";
    /** JPA定義ターゲット */
    private static final String JPA_TARGET = "persistence.xml";
    /** 非公開コンストラクタ */
    private ArchiveFactory() {
    }
    /**
     * JARの作成
     * @param packages パッケージ
     * @return JAR
     */
    public static JavaArchive createJar(final String... packages) {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        if (packages.length == 0) {
            jar.addPackages(true, BASE_PACKAGE);
        } else {
            jar.addPackages(true, packages);
        }
        jar.addAsResource("throwable-messages.properties");
        jar.addAsManifestResource(CDI_SOURCE, CDI_TARGET);
        return jar;
    }
    /**
     * JARの作成
     * @param packages パッケージ
     * @return JAR
     */
    public static JavaArchive createJarWithJpa(final String... packages) {
        final JavaArchive jar = createJar(packages);
        jar.addAsManifestResource(JPA_SOURCE, JPA_TARGET);
        return jar;
    }
}
