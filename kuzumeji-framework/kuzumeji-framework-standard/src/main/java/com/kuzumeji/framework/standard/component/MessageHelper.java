// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.apache.commons.lang3.StringUtils.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * メッセージヘルパー
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>テンプレート定義(message_*.properties)へ、メッセージのキーとテンプレートを準備すること。</li>
 * <li>テンプレート定義は、JVMインスタンスのデフォルトロケール分が取得できること。</li>
 * <li>テンプレート定義は、実行時スレッドのコンテキストクラスローダから取得できること。</li>
 * <li>テンプレート定義がないとき、{@link ExceptionInInitializerError} が返却されること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public final class MessageHelper {
    /** テンプレート */
    private static final ResourceBundle TMPL;
    /** 初期処理 */
    static {
        TMPL = ResourceBundle.getBundle("message", Locale.getDefault(), Thread.currentThread()
            .getContextClassLoader());
    }
    /** 非公開コンストラクタ */
    private MessageHelper() {
    }
    /**
     * メッセージ構築
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>テンプレートキーが空でないこと。(違反時は {@link AssertionError} が返却されること)</li>
     * <li>テンプレートキーから該当テンプレートメッセージを取得して、オブジェクトを展開したメッセージが構築されること。</li>
     * <li>該当テンプレートメッセージが取得できないときは {@link java.util.MissingResourceException} が返却されること。</li>
     * </ol>
     * </dl>
     * @param templateKey テンプレートキー
     * @param arguments オブジェクト(可変長引数)
     * @return メッセージ
     */
    public static String templateMessage(final String templateKey, final Object... arguments) {
        assert isNotBlank(templateKey);
        return createMessage(TMPL.getString(templateKey), arguments);
    }
    /**
     * メッセージ構築
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>テンプレートメッセージが空でないこと。(違反したときは {@link AssertionError} が返却されること)</li>
     * <li>テンプレートメッセージへオブジェクトを展開したメッセージが構築されること。</li>
     * <li>テンプレートメッセージが間違っているときは {@link IllegalArgumentException} が返却されること。</li>
     * </ol>
     * </dl>
     * @param templateMessage テンプレートメッセージ
     * @param arguments オブジェクト(可変長引数)
     * @return メッセージ
     */
    public static String createMessage(final String templateMessage, final Object... arguments) {
        assert isNotBlank(templateMessage);
        return MessageFormat.format(templateMessage, arguments);
    }
}
