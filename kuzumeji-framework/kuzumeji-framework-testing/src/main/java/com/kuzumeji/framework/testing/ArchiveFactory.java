// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import java.util.Iterator;
import java.util.logging.Logger;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
/**
 * アーカイブのファクトリー
 * @author nilcy
 */
public final class ArchiveFactory {
    /** CDI定義ファイル */
    private static final String CDI_TARGET = "beans.xml";
    /** ロガー */
    private static final Logger LOG = Logger.getGlobal();
    /** ベースパッケージ */
    private static final String BASE_PACKAGE = "com.kuzumeji";
    /** 非公開コンストラクタ */
    private ArchiveFactory() {
    }
    /**
     * JARの作成
     * <dl>
     * <dt>使用条件
     * <dd>パッケージ未指定のとき{@value #BASE_PACKAGE}を再帰的に追加する。
     * </dl>
     * @param packages パッケージ(サブパッケージを再帰的に追加)
     * @return JAR
     */
    public static JavaArchive createJar(final String... packages) {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class);
        if (packages.length == 0) {
            jar.addPackages(true, BASE_PACKAGE);
        } else {
            jar.addPackages(true, packages);
        }
        traceArchivePath(jar.getContent().keySet().iterator());
        return jar;
    }
    /**
     * JARの作成
     * <dl>
     * <dt>使用条件
     * <dd>{@link #createJar(String...)}へ委譲して、空のCDI定義("beans.xml")を追加する。
     * </dl>
     * @param packages パッケージ(サブパッケージを再帰的に追加)
     * @return JAR
     */
    public static JavaArchive createJarWithCdi(final String... packages) {
        final JavaArchive jar = createJar(packages);
        jar.addAsManifestResource(EmptyAsset.INSTANCE, CDI_TARGET);
        return jar;
    }
    /**
     * WARの作成
     * <dl>
     * <dt>使用条件
     * <dd>パッケージ未指定のとき{@value #BASE_PACKAGE}を再帰的に追加する。
     * </dl>
     * @param packages パッケージ(サブパッケージを再帰的に追加)
     * @return WAR
     */
    public static WebArchive createWar(final String... packages) {
        final WebArchive war = ShrinkWrap.create(WebArchive.class);
        if (packages.length == 0) {
            war.addPackages(true, BASE_PACKAGE);
        } else {
            war.addPackages(true, packages);
        }
        traceArchivePath(war.getContent().keySet().iterator());
        return war;
    }
    /**
     * WARの作成
     * <dl>
     * <dt>使用条件
     * <dd>{@link #createWar(String...)}へ委譲して、空のCDI定義("beans.xml")を追加する。
     * </dl>
     * @param packages パッケージ(サブパッケージを再帰的に追加)
     * @return JAR
     */
    public static WebArchive createWarWithCdi(final String... packages) {
        final WebArchive war = createWar(packages);
        war.addAsWebInfResource(EmptyAsset.INSTANCE, CDI_TARGET);
        return war;
    }
    /**
     * アーカイブパスのトレース
     * @param iter アーカイブパスのイテレータ
     */
    private static void traceArchivePath(final Iterator<ArchivePath> iter) {
        final StringBuilder builder = new StringBuilder();
        while (iter.hasNext()) {
            final ArchivePath path = iter.next();
            builder.append(String.format("%s\n", path.get()));
        }
        LOG.finest(builder.toString());
    }
}
