// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.mail.Session;
/**
 * メールサービスのプロデューサー
 * <dl>
 * <dt>使用条件
 * <dd><code>@Inject MailService mailService;</code>のようにDIで使用すること。
 * </dl>
 * @author nilcy
 */
public class MailServiceProducer {
    /** メールセッション */
    @Resource(name = "mail/session")
    private Session session;
    /** コンストラクタ */
    public MailServiceProducer() {
    }
    /**
     * メールサービスの作成
     * @return メールサービス
     */
    @Produces
    @Default
    public MailService createMailService() {
        return new MailServiceImpl(session);
    }
}
