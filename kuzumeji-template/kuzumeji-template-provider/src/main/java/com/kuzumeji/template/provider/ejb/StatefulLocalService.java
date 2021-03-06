// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.ejb;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
/**
 * ステートフルセッションBeanサービス(ローカルBean)
 * @author nilcy
 */
@Stateful
@LocalBean
@SuppressWarnings("static-method")
public class StatefulLocalService {
    /** コンストラクタ */
    public StatefulLocalService() {
    }
    /**
     * 挨拶メソッド
     * @param name 名前
     * @return 挨拶文(例:こんにちは %s さん。)
     */
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
