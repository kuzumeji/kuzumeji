// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import com.kuzumeji.framework.standard.component.Service;
/**
 * メールサービスI/F
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>メールサービスは本I/Fを実装すること。</li>
 * <li>{@link Service サービスI/F} を実装すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public interface MailService extends Service {
    /**
     * テキストメール送信
     * @param from FROMアドレス
     * @param recipients 宛先アドレス
     * @param subject メール件名
     * @param body メール本文(テキスト)
     * @throws EnterpriseException 指定アドレスの不正、またはメール送信の失敗
     */
    void send(final InternetAddress from, final Map<RecipientType, InternetAddress> recipients,
        final String subject, final String body) throws EnterpriseException;
    /**
     * MIMEタイプ指定メール送信
     * @param from FROMアドレス
     * @param recipients 宛先アドレス
     * @param subject メール件名
     * @param body メール本文(オブジェクト)
     * @param type メール本文のMIMEタイプ
     * @throws EnterpriseException 指定アドレスの不正、またはメール送信の失敗
     */
    void send(final InternetAddress from, final Map<RecipientType, InternetAddress> recipients,
        final String subject, final Object body, final String type) throws EnterpriseException;
    /**
     * マルチパートメール送信
     * @param from FROMアドレス
     * @param recipients 宛先アドレス
     * @param subject メール件名
     * @param body メール本文(マルチパート)
     * @throws EnterpriseException 指定アドレスの不正、またはメール送信の失敗
     */
    void send(final InternetAddress from, final Map<RecipientType, InternetAddress> recipients,
        final String subject, final Multipart body) throws EnterpriseException;
    /**
     * インターネットアドレスの作成
     * @param address メールアドレス(RFC833形式)
     * @param personal 個人名
     * @return インターネットアドレス
     * @throws UnsupportedEncodingException エンコーディング例外
     */
    InternetAddress createAddress(final String address, final String personal)
        throws UnsupportedEncodingException;
}
