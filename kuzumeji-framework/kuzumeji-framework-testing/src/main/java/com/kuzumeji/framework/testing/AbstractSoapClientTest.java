// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.jboss.arquillian.test.api.ArquillianResource;
/**
 * SOAPクライアント基底テスト
 * @author nilcy
 */
public abstract class AbstractSoapClientTest {
    /** SOAPネームスペース */
    private final String uri;
    /** SOAPサービス名 */
    private final String name;
    /** 基点URL */
    @ArquillianResource
    private URL base;
    /**
     * コンストラクタ
     * @param uri {@link #uri}
     * @param name {@link #name}
     */
    public AbstractSoapClientTest(final String uri, final String name) {
        this.uri = uri;
        this.name = name;
    }
    /**
     * ポートの取得
     * @param <T> エンドポイントI/F型
     * @param endpoint エンドポイントI/F
     * @return ポート
     */
    public <T> T getPort(final Class<T> endpoint) {
        try {
            final URL url = new URL(base.toURI() + name + "?wsdl");
            final QName qname = new QName(uri, name);
            final Service service = Service.create(url, qname);
            return service.getPort(endpoint);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
