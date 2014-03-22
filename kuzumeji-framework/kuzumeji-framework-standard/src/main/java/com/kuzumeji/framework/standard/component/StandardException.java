// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 標準キャッチ例外
 * <dl>
 * <dt>使用条件
 * <dd>キャッチ例外でメッセージマップを保有できて、{@link MessageHelper} でメッセージが構築できること。
 * </dl>
 * @author nilcy
 */
public class StandardException extends Exception {
    /** 識別番号 */
    private static final long serialVersionUID = 7615078242077460244L;
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(StandardException.class);
    /**
     * メッセージマップ
     * <dl>
     * <dt>使用条件
     * <dd>メッセージのキーとオブジェクト配列という形式であること。
     * </dl>
     */
    private final Map<String, Object[]> messageMap;
    /** コンストラクタ */
    public StandardException() {
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public StandardException(final String message) {
        super(message);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * @param cause 例外オブジェクト
     */
    public StandardException(final Throwable cause) {
        super(cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * @param message メッセージ
     * @param cause 例外オブジェクト
     */
    public StandardException(final String message, final Throwable cause) {
        super(message, cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * @param message メッセージ
     * @param cause 例外オブジェクト
     * @param enableSuppression 抑制の有無
     * @param writableStackTrace スタックトレース書込の可否
     */
    public StandardException(final String message, final Throwable cause,
        final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>キーをもとに {@link Exception#Exception(String)} へ委譲されること。</li>
     * <li>キーとオブジェクト配列が {@link #messageMap} へ格納されること。</li>
     * </ol>
     * </dl>
     * @param key キー
     * @param values オブジェクト配列
     */
    public StandardException(final String key, final Object... values) {
        super(key);
        messageMap = new LinkedHashMap<>();
        messageMap.put(key, values);
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>メッセージマップの先頭キーをもとに {@link Exception#Exception(String)} へ委譲されること。</li>
     * <li>{@link #messageMap} がメッセージマップで初期化されること。</li>
     * </ol>
     * </dl>
     * @param messageMap {@link #messageMap}
     */
    public StandardException(final Map<String, Object[]> messageMap) {
        super(messageMap.keySet().iterator().next());
        this.messageMap = messageMap;
    }
    /**
     * {@link #messageMap} の取得
     * @return {@link #messageMap}
     */
    public Map<String, Object[]> getMessageMap() {
        return messageMap;
    }
    /**
     * アプリケーションメッセージの取得
     * @return アプリケーションメッセージ
     */
    public String getApplicationMessage() {
        final PropertiesHelper props = new PropertiesHelper("message.properties");
        try {
            final Entry<String, Object[]> entry = getMessageMap().entrySet().iterator().next();
            return props.getText(entry.getKey(), entry.getValue());
        } catch (final NoSuchElementException e) {
            LOG.warn(e.getLocalizedMessage());
            return null;
        }
    }
}
