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
    /** 非公開コンストラクタ */
    private ArchiveFactory() {
    }
    /**
     * JARの作成
     * @return JAR
     */
    public static JavaArchive createJar() {
        return ShrinkWrap.create(JavaArchive.class).addPackages(true, "com.kuzumeji")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
}
