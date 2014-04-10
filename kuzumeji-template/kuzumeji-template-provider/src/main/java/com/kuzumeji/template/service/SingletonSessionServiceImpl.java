// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.service;
import java.util.concurrent.TimeUnit;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
/**
 * シングルトンセッションBeanサービス
 * @author nilcy
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
@AccessTimeout(value = 10, unit = TimeUnit.SECONDS)
public class SingletonSessionServiceImpl implements SingletonSessionService,
    SingletonSessionServiceRemote {
    /** コンストラクタ */
    public SingletonSessionServiceImpl() {
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
