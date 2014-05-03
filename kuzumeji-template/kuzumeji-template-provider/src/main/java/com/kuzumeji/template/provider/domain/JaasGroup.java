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
 * JAASグループ
 * @author nilcy
 */
@Entity
@Table(name = "JaasGroup", uniqueConstraints = { @UniqueConstraint(columnNames = { "groupName",
    "userName" }) })
@NamedQueries({ @NamedQuery(name = "JaasGroup.findUK_groupName_userName", query = "SELECT e FROM JaasGroup e WHERE e.groupName=:groupName AND e.userName=:userName") })
public class JaasGroup extends AbstractPersistable<JaasGroup> {
    /** 識別番号 */
    private static final long serialVersionUID = -2989313024762807768L;
    /** グループ名 */
    @Column(name = "groupName", nullable = false)
    private String groupName;
    /** ユーザ名 */
    @Column(name = "userName", nullable = false)
    private String userName;
    /** コンストラクタ */
    public JaasGroup() {
    }
    /**
     * コンストラクタ
     * @param groupName {@link #groupName}
     * @param userName {@link #userName}
     */
    public JaasGroup(final String groupName, final String userName) {
        this.groupName = groupName;
        this.userName = userName;
    }
    /**
     * {@link #groupName} の取得
     * @return {@link #groupName}
     */
    protected final String getGroupName() {
        return groupName;
    }
    /**
     * {@link #groupName} の設定
     * @param groupName {@link #groupName}
     */
    protected final void setGroupName(final String groupName) {
        this.groupName = groupName;
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
}
