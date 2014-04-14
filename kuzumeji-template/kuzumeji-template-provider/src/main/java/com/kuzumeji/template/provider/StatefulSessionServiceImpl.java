// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider;
import java.util.concurrent.TimeUnit;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import com.kuzumeji.template.service.StatefulSessionService;
import com.kuzumeji.template.service.StatefulSessionServiceRemote;
/**
 * ステートフルセッションBeanサービス
 * @author nilcy
 */
@Stateful
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
@AccessTimeout(value = 10, unit = TimeUnit.SECONDS)
public class StatefulSessionServiceImpl implements StatefulSessionService,
    StatefulSessionServiceRemote {
    /** コンストラクタ */
    public StatefulSessionServiceImpl() {
    }
    /** {@inheritDoc} */
    @Override
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
