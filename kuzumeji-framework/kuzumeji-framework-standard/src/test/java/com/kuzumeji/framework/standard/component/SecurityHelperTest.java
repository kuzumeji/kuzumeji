// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.junit.Test;
/**
 * @see SecurityHelper
 * @author nilcy
 */
@SuppressWarnings("all")
public class SecurityHelperTest {
    @Test
    public void test() {
        final KeyPair keyPair = SecurityHelper.createKeyPair();
        assertThat(keyPair, is(not(nullValue())));
        final RSAPublicKey publicKey = SecurityHelper.createPublicKey(
            ((RSAPublicKey) keyPair.getPublic()).getModulus(),
            ((RSAPublicKey) keyPair.getPublic()).getPublicExponent());
        final RSAPrivateKey privateKey = SecurityHelper.createPrivateKey(
            ((RSAPrivateKey) keyPair.getPrivate()).getModulus(),
            ((RSAPrivateKey) keyPair.getPrivate()).getPrivateExponent());
        assertThat(publicKey.getEncoded(), is(keyPair.getPublic().getEncoded()));
        final SecretKey secretKey = SecurityHelper.createSecretKey("hello, world!!".toCharArray(),
            new byte[] { 1 });
        assertThat(secretKey, is(not(nullValue())));
        final byte[] encrypted = SecurityHelper.encrypt(publicKey, secretKey.getEncoded());
        final byte[] decrypted = SecurityHelper.decrypt(privateKey, encrypted);
        assertThat(decrypted, is(secretKey.getEncoded()));
        // ----
        final KeyPair signKeyPair = SecurityHelper.createSignKeyPair();
        final byte[] sign = SecurityHelper.sign(signKeyPair.getPrivate(), "DATA".getBytes());
        assertThat(SecurityHelper.verify(signKeyPair.getPublic(), sign, "DATA".getBytes()),
            is(true));
        // ----
        final SecretKey secretKey2 = SecurityHelper.createSecretKey("1234567890123456".getBytes());
        final IvParameterSpec iv = new IvParameterSpec("abcdefghijklmnop".getBytes());
        final byte[] encrypted2 = SecurityHelper.encrypt(secretKey2, iv, "BIGDATA".getBytes());
        final byte[] decrypted2 = SecurityHelper.decrypt(secretKey2, iv, encrypted2);
        assertThat(decrypted2, is("BIGDATA".getBytes()));
    }
}
