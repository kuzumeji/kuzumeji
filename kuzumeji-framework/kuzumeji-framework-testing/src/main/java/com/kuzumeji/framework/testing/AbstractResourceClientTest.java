// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.testing;
import java.net.URISyntaxException;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.jboss.arquillian.test.api.ArquillianResource;
/**
 * RESTクライアント基底テスト
 * @author nilcy
 */
public abstract class AbstractResourceClientTest {
    /** RESTパス */
    private final String path;
    /** RESTクライアント */
    private Client client;
    /** RESTルート */
    private WebTarget root;
    /** 基点URL */
    @ArquillianResource
    private URL base;
    /**
     * コンストラクタ
     * @param path {@link #path}
     */
    public AbstractResourceClientTest(final String path) {
        this.path = path;
    }
    /**
     * {@link #client}の取得
     * @return {@link #client}
     */
    protected synchronized Client client() {
        if (client == null) {
            client = ClientBuilder.newClient();
        }
        return client;
    }
    /**
     * {@link #root}の取得
     * @return {@link #root}
     * @throws URISyntaxException URI構文例外
     */
    protected synchronized WebTarget root() throws URISyntaxException {
        if (root == null) {
            root = client().target(base.toURI() + path);
        }
        return root;
    }
}
