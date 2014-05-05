// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import com.kuzumeji.framework.standard.component.AbstractValueObject;
/**
 * テストエンティティ検索条件
 * @author nilcy
 */
public class TesteeFilter extends AbstractValueObject<TesteeFilter> {
    /** 識別番号 */
    private static final long serialVersionUID = 5270677421769642756L;
    /** コード */
    private final String code;
    /**
     * コンストラクタ
     * @param code {@link #code}
     */
    public TesteeFilter(final String code) {
        this.code = code;
    }
    /**
     * {@link #code} の取得
     * @return {@link #code}
     */
    public final String getCode() {
        return code;
    }
}