// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.kuzumeji.framework.enterprise.component.persistence.AbstractPersistable;
/**
 * @see AbstractPersistable
 * @author nilcy
 */
@Entity
@Table(name = "PersitenceTestee")
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
    public void setName(String name) {
        this.name = name;
    }
}
