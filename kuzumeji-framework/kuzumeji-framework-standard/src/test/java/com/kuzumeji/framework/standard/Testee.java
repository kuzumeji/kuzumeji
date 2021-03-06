// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard;
/**
 * @author nilcy
 */
public class Testee {
    /** foo */
    private String foo;
    /** bar */
    private String bar;
    /** baz */
    private String baz;
    /** コンストラクタ */
    public Testee() {
    }
    /**
     * コンストラクタ
     * @param foo {@link #foo}
     * @param bar {@link #bar}
     * @param baz {@link #baz}
     */
    public Testee(final String foo, final String bar, final String baz) {
        this.foo = foo;
        this.bar = bar;
        this.baz = baz;
    }
    /**
     * {@link #foo} の取得
     * @return {@link #foo}
     */
    public String getFoo() {
        return foo;
    }
    /**
     * {@link #foo} の設定
     * @param foo {@link #foo}
     */
    public void setFoo(final String foo) {
        this.foo = foo;
    }
    /**
     * {@link #bar} の取得
     * @return {@link #bar}
     */
    public String getBar() {
        return bar;
    }
    /**
     * {@link #bar} の設定
     * @param bar {@link #bar}
     */
    public void setBar(final String bar) {
        this.bar = bar;
    }
    /**
     * {@link #baz} の取得
     * @return {@link #baz}
     */
    public String getBaz() {
        return baz;
    }
    /**
     * {@link #baz} の設定
     * @param baz {@link #baz}
     */
    public void setBaz(final String baz) {
        this.baz = baz;
    }
}
