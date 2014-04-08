// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * カバレッジヘルパー
 * @author nilcy
 */
public final class CoverageHelper {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(CoverageHelper.class);
    /** 非公開コンストラクタ */
    private CoverageHelper() {
    }
    /**
     * 非公開コンストラクタ実行
     * <dl>
     * <dt>使用条件
     * <dd>カバレッジ取得するために使用すること。(注:privateクラスは使用不可)
     * </dl>
     * @param clazz クラス
     */
    public static void privateConstructor(final Class<?> clazz) {
        try {
            final Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            constructor.setAccessible(false);
        } catch (NoSuchMethodException | SecurityException | InstantiationException
            | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LOG.warn(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
