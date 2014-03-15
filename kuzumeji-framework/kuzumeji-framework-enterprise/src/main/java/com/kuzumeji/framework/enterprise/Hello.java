// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise;
/**
 * サンプルクラス
 * @author nilcy
 */
public final class Hello {
    /** 非公開コンストラクタ */
    private Hello() {
    }
    /**
     * サンプルメソッド
     * @param name 名前
     * @return こんにちは %s さん。
     */
    public static String sayHello(final String name) {
        return String.format("こんにちは %s さん。", name);
    }
}
