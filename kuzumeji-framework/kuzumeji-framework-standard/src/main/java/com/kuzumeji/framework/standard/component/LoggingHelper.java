// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
/**
 * ロギングヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>TODO 各操作前後の不変条件を表明すること。(DDD/契約による設計)
 * </dl>
 * @author nilcy
 */
public final class LoggingHelper {
    /** 非公開コンストラクタ */
    private LoggingHelper() {
    }
    /**
     * グローバルロガーの取得
     * <dl>
     * <dt>使用条件
     * <dd>標準出力へ全ログレベルが出力されるロガーが返却されること。
     * </dl>
     * @return グローバルロガー
     */
    public static Logger getGlobal() {
        final Logger logger = Logger.getGlobal();
        logger.addHandler(new StreamHandler() {
            {
                setOutputStream(System.out);
                setLevel(Level.ALL);
            }
        });
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        return logger;
    }
}
