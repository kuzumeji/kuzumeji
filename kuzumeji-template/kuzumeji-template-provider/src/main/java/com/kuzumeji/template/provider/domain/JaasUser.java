// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.kuzumeji.framework.enterprise.component.persistence.AbstractPersistable;
/**
 * JAASユーザ
 * @author nilcy
 */
@Entity
@Table(name = "JaasUser", uniqueConstraints = { @UniqueConstraint(columnNames = { "userName" }) })
@NamedQueries({ @NamedQuery(name = "JaasUser.findUK_userName", query = "SELECT e FROM JaasUser e WHERE e.userName=:userName") })
public class JaasUser extends AbstractPersistable<JaasUser> {
    /** 識別番号 */
    private static final long serialVersionUID = -6427550988593985865L;
    /** ユーザ名 */
    @Column(name = "userName", nullable = false)
    private String userName;
    /** パスワード */
    @Column(name = "password", nullable = true)
    private String password;
    /** コンストラクタ */
    public JaasUser() {
    }
    /**
     * コンストラクタ
     * @param userName {@link #userName}
     * @param password {@link #password}
     */
    public JaasUser(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }
    /**
     * {@link #userName} の取得
     * @return {@link #userName}
     */
    protected final String getUserName() {
        return userName;
    }
    /**
     * {@link #userName} の設定
     * @param userName {@link #userName}
     */
    protected final void setUserName(final String userName) {
        this.userName = userName;
    }
    /**
     * {@link #password} の取得
     * @return {@link #password}
     */
    protected final String getPassword() {
        return password;
    }
    /**
     * {@link #password} の設定
     * @param password {@link #password}
     */
    protected final void setPassword(final String password) {
        this.password = password;
    }
}
