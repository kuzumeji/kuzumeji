// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static com.kuzumeji.framework.standard.component.ConfigHelper.*;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
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
    /** 文字セット */
    private static final String CHARSET = "UTF-8";
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
    public final void send(final InternetAddress from,
        final Map<RecipientType, InternetAddress> recipients, final String subject,
        final Object body, final String mimeType) throws EnterpriseException {
        try {
            final Message message = createMessage(from, recipients, subject);
            message.setContent(body, mimeType);
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e);
        }
    }
    /** {@inheritDoc} */
    @Override
    public final void send(final InternetAddress from,
        final Map<RecipientType, InternetAddress> recipients, final String subject,
        final String body) throws EnterpriseException {
        try {
            final Message message = createMessage(from, recipients, subject);
            message.setContent(body, "text/plain;charset=UTF-8");
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e);
        }
    }
    /** {@inheritDoc} */
    @Override
    public final void send(final InternetAddress from,
        final Map<RecipientType, InternetAddress> recipients, final String subject,
        final Multipart body) throws EnterpriseException {
        try {
            final Message message = createMessage(from, recipients, subject);
            message.setContent(body);
            send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException(e);
        }
    }
    /**
     * メッセージの作成
     * @param from FROMアドレス
     * @param recipients 宛先アドレス
     * @param subject メール件名
     * @return メッセージ
     * @throws EnterpriseException メッセージの例外
     */
    private Message createMessage(final InternetAddress from,
        final Map<RecipientType, InternetAddress> recipients, final String subject)
        throws EnterpriseException {
        Validate.isTrue(recipients.size() > 0);
        try {
            final MimeMessage message = new MimeMessage(session);
            if (from != null) {
                message.setFrom(from);
            }
            for (final Entry<RecipientType, InternetAddress> recipient : recipients.entrySet()) {
                message.addRecipient(recipient.getKey(), recipient.getValue());
            }
            message.setSubject(subject, CHARSET);
            return message;
        } catch (final MessagingException e) {
            throw new EnterpriseException(e);
        }
    }
    /**
     * メール送信
     * @param message メッセージ
     * @throws EnterpriseException メッセージの例外
     */
    private static void send(final Message message) throws EnterpriseException {
        Validate.notNull(message);
        try {
            Transport.send(message);
        } catch (final MessagingException e) {
            throw new EnterpriseException();
        }
    }
    /** {@inheritDoc} */
    @Override
    public InternetAddress createAddress(final String address, final String personal)
        throws UnsupportedEncodingException {
        return new InternetAddress(address, personal, INET_ADDRESS_CHARSET);
    }
}
