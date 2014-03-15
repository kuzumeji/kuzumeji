// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterpise.component;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
/**
 * アーカイブのファクトリー
 * @author nilcy
 */
final class ArchiveFactory {
    /** 非公開コンストラクタ */
    private ArchiveFactory() {
    }
    /**
     * アーカイブの作成
     * @return アーカイブ
     */
    static JavaArchive create() {
        return ShrinkWrap.create(JavaArchive.class)
            .addPackages(true, "com.kuzumeji.framework.standard")
            .addPackages(true, "com.kuzumeji.framework.enterprise.component")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
}
