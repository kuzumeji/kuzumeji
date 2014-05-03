// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;
import com.kuzumeji.framework.standard.component.AbstractValueObject;
/**
 * JAASユーザドメイン
 * @author nilcy
 */
public class JaasUserDomain extends AbstractValueObject<JaasUserDomain> {
    /** 識別番号 */
    private static final long serialVersionUID = -7875471541734976140L;
    /** JAASユーザ */
    private final JaasUser user;
    /**
     * コンストラクタ
     * @param user {@link #user}
     */
    public JaasUserDomain(final JaasUser user) {
        this.user = user;
    }
    /**
     * パスワード変更
     * <dl>
     * <dt>使用条件
     * <dd>新パスワードが空でないこと、新旧パスワードが同一でないことが検証されること。新しいJAASユーザが返却されること。
     * </dl>
     * @param password 新パスワード
     * @return 新しいJAASユーザ
     */
    public JaasUser changePassword(final String password) {
        Validate.notBlank(password);
        final String newPassword = DigestUtils.sha256Hex(password);
        Validate.isTrue(!newPassword.equals(user.getPassword()), "The validated password is same");
        return new JaasUser(user.getUserName(), newPassword);
    }
}
