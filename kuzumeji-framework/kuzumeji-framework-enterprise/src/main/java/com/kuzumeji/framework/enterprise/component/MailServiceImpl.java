// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.Validate;
/**
 * メールサービス
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>{@link MailService メールサービスI/F} が実装されること。</li>
 * <li>{@link #MailServiceImpl(Session) コンストラクタ} でインスタンス化すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public class MailServiceImpl implements MailService {
    /** メールセッション */
    private final Session session;
    /**
     * コンストラクタ
     * @param session {@link #session メールセッション}
     */
    public MailServiceImpl(final Session session) {
        Validate.notNull(session, "メールセッションは非NULLでなくてはならない。");
        this.session = session;
    }
    /** {@inheritDoc} */
    @Override
    public final void send(final String from, final String to, final String subject,
        final Object objectBody, final String contentType) throws EnterpriseException {
        try {
            final Message message = createMessage(from, to, subject);
            message.setContent(objectBody, contentType);
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        }
    }
    /** {@inheritDoc} */
    @Override
    public final void send(final String from, final String to, final String subject,
        final String textBody) throws EnterpriseException {
        try {
            final Message message = createMessage(from, to, subject);
            message.setContent(textBody, "text/plain");
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        }
    }
    /** {@inheritDoc} */
    @Override
    public final void send(final String from, final String to, final String subject,
        final Multipart multipartBody) throws EnterpriseException {
        try {
            final Message message = createMessage(from, to, subject);
            message.setContent(multipartBody);
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        }
    }
    /**
     * メッセージの作成
     * @param from FROMアドレス
     * @param to TOアドレス
     * @param subject メール件名
     * @return メッセージ
     * @throws EnterpriseException 指定アドレス、メッセージの例外
     */
    private Message createMessage(final String from, final String to, final String subject)
        throws EnterpriseException {
        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            return message;
        } catch (final AddressException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        } catch (final MessagingException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        }
    }
    /**
     * メール送信
     * @param message メッセージ
     * @throws EnterpriseException メッセージの例外
     */
    private static void send(final Message message) throws EnterpriseException {
        try {
            Transport.send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e.getLocalizedMessage());
        }
    }
}
