// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain.auth;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.kuzumeji.framework.enterprise.component.persistence.AbstractVersionable;
/**
 * 認証エンティティ
 * @author nilcy
 */
@Entity
@Table(name = "Certification", uniqueConstraints = { @UniqueConstraint(columnNames = { "account" }) })
@NamedQueries({ @NamedQuery(name = "Certification.findUK_account", query = "SELECT e FROM Certification e WHERE e.account=:account") })
public class Certification extends AbstractVersionable<Certification> {
    /** 識別番号 */
    private static final long serialVersionUID = -6427550988593985865L;
    /** アカウント */
    @Column(name = "account", nullable = false)
    private String account;
    /** パスワード */
    @Column(name = "password", nullable = true)
    private String password;
    /** 認可エンティティ集合 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id", nullable = true, insertable = true, updatable = true)
    private Collection<Permission> auths;
    /** コンストラクタ */
    public Certification() {
    }
    /**
     * コンストラクタ
     * @param account {@link #account}
     * @param password {@link #password}
     */
    public Certification(final String account, final String password) {
        this.account = account;
        this.password = password;
    }
    /**
     * {@link #account} の取得
     * @return {@link #account}
     */
    public final String getAccount() {
        return account;
    }
    /**
     * {@link #account} の設定
     * @param account {@link #account}
     */
    public final void setAccount(final String account) {
        this.account = account;
    }
    /**
     * {@link #password} の取得
     * @return {@link #password}
     */
    public final String getPassword() {
        return password;
    }
    /**
     * {@link #password} の設定
     * @param password {@link #password}
     */
    public final void setPassword(final String password) {
        this.password = password;
    }
}
