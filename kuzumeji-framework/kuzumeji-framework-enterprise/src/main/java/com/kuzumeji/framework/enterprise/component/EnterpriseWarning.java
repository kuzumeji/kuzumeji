// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.ApplicationException;
import com.kuzumeji.framework.standard.component.PropertiesHelper;
import com.kuzumeji.framework.standard.component.StandardException;
/**
 * 基幹キャッチ例外
 * <dl>
 * <dt>使用条件
 * <dd>{@link StandardException} へ準拠すること。ロールバックなし、継承あり。
 * </dl>
 * @author nilcy
 */
@ApplicationException(rollback = false, inherited = true)
public class EnterpriseWarning extends StandardException {
    /** 識別番号 */
    private static final long serialVersionUID = -6004059900614805028L;
    /** メッセージ定義ベース名 */
    private static final String MESSAGE_BASENAME = "throwable-messages";
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
     * <dd>{@link #messageMap}が{@link LinkedHashMap#LinkedHashMap()}で初期化されること。
     * </dl>
     */
    public EnterpriseWarning() {
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>{@link Exception#Exception(String)}へ委譲されること。</li>
     * <li>{@link #messageMap}が{@link LinkedHashMap#LinkedHashMap()}で初期化されること。</li>
     * </ol>
     * </dl>
     * @param message メッセージ
     */
    public EnterpriseWarning(final String message) {
        super(message);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>{@link Exception#Exception(Throwable)}へ委譲されること。</li>
     * <li>{@link #messageMap}が{@link LinkedHashMap#LinkedHashMap()}で初期化されること。</li>
     * </ol>
     * </dl>
     * @param cause 例外オブジェクト
     */
    public EnterpriseWarning(final Throwable cause) {
        super(cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>{@link Exception#Exception(String, Throwable)}へ委譲されること。</li>
     * <li>{@link #messageMap}が{@link LinkedHashMap#LinkedHashMap()}で初期化されること。</li>
     * </ol>
     * </dl>
     * @param message メッセージ
     * @param cause 例外オブジェクト
     */
    public EnterpriseWarning(final String message, final Throwable cause) {
        super(message, cause);
        messageMap = new LinkedHashMap<>();
    }
    /**
     * コンストラクタ
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>{@link Exception#Exception(String, Throwable, boolean, boolean)}へ委譲されること。</li>
     * <li>{@link #messageMap}が{@link LinkedHashMap#LinkedHashMap()}で初期化されること。</li>
     * </ol>
     * </dl>
     * @param message メッセージ
     * @param cause 例外オブジェクト
     * @param enableSuppression 抑制の有無
     * @param writableStackTrace スタックトレース書込の可否
     */
    public EnterpriseWarning(final String message, final Throwable cause,
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
     * <li>{@link #messageMap} が {@link LinkedHashMap#LinkedHashMap()} で初期化されること。</li>
     * <li>キーとオブジェクト配列が {@link #messageMap} へ格納されること。</li>
     * </ol>
     * </dl>
     * @param key キー
     * @param values オブジェクト配列
     */
    public EnterpriseWarning(final String key, final Object... values) {
        super(new PropertiesHelper(MESSAGE_BASENAME).getText(key, values));
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
    public EnterpriseWarning(final Map<String, Object[]> messageMap) {
        super(messageMap.keySet().iterator().next());
        this.messageMap = messageMap;
    }
    /**
     * {@link #messageMap} の取得
     * @return {@link #messageMap}
     */
    @Override
    public Map<String, Object[]> getMessageMap() {
        return messageMap;
    }
}
