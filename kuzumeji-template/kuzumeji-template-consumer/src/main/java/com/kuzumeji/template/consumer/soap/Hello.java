// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.consumer.soap;
import javax.jws.WebMethod;
import javax.jws.WebService;
/**
 * 挨拶サービス
 * @author nilcy
 */
@WebService
public class Hello {
    /** コンストラクタ */
    public Hello() {
    }
    /**
     * 挨拶オペレーション
     * @param name 名前
     * @return こんにちは %s さん。
     */
    @WebMethod
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
