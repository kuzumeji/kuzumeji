// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.mail;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.kuzumeji.framework.enterprise.component.EnterpriseException;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see MailService
 * @see MailServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@SuppressWarnings("javadoc")
public class MailServiceImplTest {
    /** メールサービスI/F */
    @Inject
    private MailService testee;
    /** 送付元アドレス */
    private InternetAddress originator;
    /** 送付先アドレス */
    private Map<RecipientType, InternetAddress> recipients;
    /**
     * デプロイ
     * @return JAR
     */
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJarWithCdi().addAsResource("config.properties");
    }
    /** テスト前処理 */
    @Before
    public void before() {
        assertThat(testee, is(not(nullValue())));
        try {
            originator = testee.createAddress("kuzumeji@gmail.com", "苦集滅道");
            recipients = new LinkedHashMap<>();
            recipients.put(RecipientType.TO, testee.createAddress("shimokawa@mamezou.com", "下川岳洋"));
            recipients.put(RecipientType.CC, testee.createAddress("kuzumeji@gmail.com"));
        } catch (final UnsupportedEncodingException | AddressException e) {
            fail(e.toString());
        }
    }
    /**
     * @see MailServiceImpl#send(InternetAddress, Map, String, String)
     */
    @Test
    public void testText() throws EnterpriseException {
        testee.send(originator, recipients, "テキストメール", "ふがふが…\nほげほげ…\nぶろろろろ~。");
    }
    /**
     * @see MailServiceImpl#send(InternetAddress, Map, String, Object, String)
     */
    @Test
    public void testMime() throws EnterpriseException {
        testee.send(originator, recipients, "MIMEタイプ指定メール", "ふがふが…\nほげほげ…\nぶろろろろ~。",
            "text/plain;charset=UTF-8");
    }
    /**
     * @see MailServiceImpl#send(InternetAddress, Map, String, javax.mail.Multipart)
     */
    @Test
    public void testMultipart() throws EnterpriseException, MessagingException,
        UnsupportedEncodingException {
        final Multipart part = new MimeMultipart();
        final MimeBodyPart body = new MimeBodyPart();
        body.setText("添付ファイルを確認お願いします。", "ISO-2022-JP");
        part.addBodyPart(body);
        final MimeBodyPart file = new MimeBodyPart();
        final FileDataSource source = new FileDataSource(getClass().getResource(
            "/" + "config.properties").getPath());
        file.setDataHandler(new DataHandler(source));
        file.setFileName(MimeUtility.encodeWord(source.getName()));
        part.addBodyPart(file);
        testee.send(originator, recipients, "マルチパートメール", part);
    }
}
