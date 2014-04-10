// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.service;
import javax.ejb.Stateless;
/**
 * ステートレスサービス
 * @author nilcy
 */
@Stateless
public class StatelessServiceImpl implements StatelessService, StatelessServiceRemote {
    /** コンストラクタ */
    public StatelessServiceImpl() {
    }
    /** {@inheritDoc} */
    @Override
    public String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
