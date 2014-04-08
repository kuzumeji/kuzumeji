// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see MailServiceImpl
 * @author nilcy
 */
@RunWith(Arquillian.class)
@SuppressWarnings("all")
public class MailServiceImplTest {
    @Inject
    private MailService testee;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJar();
    }
    @Test
    public void test() throws EnterpriseException, AddressException, UnsupportedEncodingException {
        assertThat(testee, is(not(nullValue())));
        final InternetAddress from = new InternetAddress("kuzumeji@gmail.com", "苦集滅道", "UTF-8");
        final Map<RecipientType, InternetAddress> recipients = new LinkedHashMap<>();
        recipients.put(RecipientType.TO, new InternetAddress("shimokawa@mamezou.com", "下川岳洋",
            "UTF-8"));
        testee.send(from, recipients, "テストメール題名", "テストメール本文");
    }
}
