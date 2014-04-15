// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.soap;
/**
 * 挨拶サービスI/F
 * @author nilcy
 */
public interface HelloService {
    /**
     * 挨拶オペレーション
     * @param name 名前
     * @return こんにちは %s さん。
     */
    String sayHello(String name);
}
