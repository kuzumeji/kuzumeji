// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.registry.ejb;
import javax.ejb.Remote;
/**
 * ステートレスセッションBeanサービスI/F(リモート)
 * @author nilcy
 */
@Remote
public interface StatelessSessionServiceRemote {
    /**
     * 挨拶メソッド
     * @param name 名前
     * @return 挨拶文(例:こんにちは %s さん。)
     */
    String sayHello(final String name);
}
