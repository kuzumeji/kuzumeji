// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * テストエンティティ
 * @see AbstractPersistable
 * @author nilcy
 */
@Entity
@Table(name = "PersitenceTestee", indexes = { @Index(columnList = "code"),
    @Index(columnList = "name") }, uniqueConstraints = {
    @UniqueConstraint(columnNames = { "code" }), @UniqueConstraint(columnNames = { "name" }) })
@NamedQueries({
    @NamedQuery(name = "PersistableTestee.findUK_code", query = "SELECT pt FROM PersistableTestee pt WHERE pt.code=:code"),
    @NamedQuery(name = "PersistableTestee.findUK_name", query = "SELECT pt FROM PersistableTestee pt WHERE pt.name=:name") })
public class PersistableTestee extends AbstractPersistable<PersistableTestee> {
    /** 識別番号 */
    private static final long serialVersionUID = 4506075597459118931L;
    /** コード */
    private String code;
    /** なまえ */
    private String name;
    /** コンストラクタ */
    public PersistableTestee() {
    }
    /**
     * コンストラクタ
     * @param code {@link #code コード}
     * @param name {@link #name なまえ}
     */
    public PersistableTestee(final String code, final String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * {@link #code} の取得
     * @return {@link #code}
     */
    public String getCode() {
        return code;
    }
    /**
     * {@link #code} の設定
     * @param code {@link #code}
     */
    public void setCode(final String code) {
        this.code = code;
    }
    /**
     * {@link #name} の取得
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }
    /**
     * {@link #name} の設定
     * @param name {@link #name}
     */
    public void setName(final String name) {
        this.name = name;
    }
}
