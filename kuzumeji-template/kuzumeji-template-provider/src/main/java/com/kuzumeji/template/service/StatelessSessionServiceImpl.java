// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.service;
import javax.ejb.Stateless;
/**
 * ステートレスセッションBeanサービス
 * @author nilcy
 */
@Stateless
public class StatelessSessionServiceImpl implements StatelessSessionService,
    StatelessSessionServiceRemote {
    /** コンストラクタ */
    public StatelessSessionServiceImpl() {
    }
    /**
     * 挨拶メソッド
     * @param name 名前
     * @return 挨拶文(例:こんにちは %s さん。)
     */
    @Override
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
