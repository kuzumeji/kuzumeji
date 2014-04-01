// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 設定
 * @author nilcy
 */
@Entity
@Table(name = "config")
public class Config {
    /** 設定ID */
    @EmbeddedId
    private ConfigId id;
    /** 設定値 */
    @Column(name = "config_value", nullable = true)
    private String value;
    /** コンストラクタ */
    public Config() {
    }
    /**
     * {@link #id} の取得
     * @return {@link #id}
     */
    public ConfigId getId() {
        return id;
    }
    /**
     * {@link #id} の設定
     * @param id {@link #id}
     */
    public void setId(final ConfigId id) {
        this.id = id;
    }
    /**
     * {@link #value} の取得
     * @return {@link #value}
     */
    public String getValue() {
        return value;
    }
    /**
     * {@link #value} の設定
     * @param value {@link #value}
     */
    public void setValue(final String value) {
        this.value = value;
    }
}
