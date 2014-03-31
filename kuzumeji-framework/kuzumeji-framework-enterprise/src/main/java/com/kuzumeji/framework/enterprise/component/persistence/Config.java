// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
/**
 * 設定
 * @author nilcy
 */
@Entity
@Table(name = "config")
@IdClass(ConfigId.class)
public class Config {
    /** 設定名 */
    @Id
    private String name;
    /** 設定キー */
    @Id
    private String key;
    /** 設定値 */
    private String value;
    /** コンストラクタ */
    public Config() {
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
    /**
     * {@link #key} の取得
     * @return {@link #key}
     */
    public String getKey() {
        return key;
    }
    /**
     * {@link #key} の設定
     * @param key {@link #key}
     */
    public void setKey(final String key) {
        this.key = key;
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
