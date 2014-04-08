// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import java.text.MessageFormat;
import org.apache.commons.lang3.Validate;
/**
 * メッセージヘルパー
 * @author nilcy
 */
public final class MessageHelper {
    /** メッセージ定義 */
    private static final PropertiesHelper TMPL = new PropertiesHelper("messages");
    /** 非公開コンストラクタ */
    private MessageHelper() {
    }
    /**
     * メッセージ構築
     * @param message メッセージ
     * @param values 展開オブジェクト
     * @return メッセージ
     */
    public static String createMessage(final String message, final Object... values) {
        Validate.notBlank(message);
        return MessageFormat.format(message, values);
    }
    /**
     * メッセージ構築
     * @param key メッセージ定義キー
     * @param values 展開オブジェクト
     * @return メッセージ
     */
    public static String templateMessage(final String key, final Object... values) {
        Validate.notBlank(key);
        return createMessage(TMPL.getText(key), values);
    }
}
