// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 基本ランタイム例外
 * <dl>
 * <dt>使用条件
 * <dd>非キャッチ例外でメッセージマップを保有できて、{@link MessageHelper} でメッセージが構築できること。
 * </dl>
 * @author nilcy
 */
public class StandardRuntimeException extends RuntimeException {
    /** 識別番号 */
    private static final long serialVersionUID = -1011000372434761952L;
    /**
     * メッセージマップ
     * <dl>
     * <dt>使用条件
     * <dd>メッセージのキーとオブジェクト配列という形式であること。
     * </dl>
     */
    private final Map<String, Object[]> messageMap;
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException()} へ準拠すること。
     * </dl>
     */
    public StandardRuntimeException() {
        super();
        messageMap = null;
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(String)} へ準拠すること。
     * </dl>
     * @param message メッセージ
     */
    public StandardRuntimeException(final String message) {
        super(message);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(Throwable)} へ準拠すること。
     * </dl>
     * @param cause 例外オブジェクト
     */
    public StandardRuntimeException(final Throwable cause) {
        super(cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(String, Throwable)} へ準拠すること。
     * </dl>
     * @param message メッセージ
     * @param cause 例外オブジェクト
     */
    public StandardRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(String, Throwable, boolean, boolean)} へ準拠すること。
     * </dl>
     * @param message メッセージ
     * @param cause 例外オブジェクト
     * @param enableSuppression 抑制の有無
     * @param writableStackTrace スタックトレース書込の可否
     */
    public StandardRuntimeException(final String message, final Throwable cause,
        final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(String, Object...)} へ準拠すること。
     * </dl>
     * @param key キー
     * @param values オブジェクト配列
     */
    public StandardRuntimeException(final String key, final Object... values) {
        super(key);
        messageMap = new LinkedHashMap<>();
        messageMap.put(key, values);
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>{@link StandardException#StandardException(Map)} へ準拠すること。
     * </dl>
     * @param messageMap {@link #messageMap}
     */
    public StandardRuntimeException(final Map<String, Object[]> messageMap) {
        super(messageMap.keySet().iterator().next());
        this.messageMap = messageMap;
    }
    /**
     * {@link #messageMap} の取得
     * @return {@link #messageMap}
     */
    public final Map<String, Object[]> getMessageMap() {
        return messageMap;
    }
}
