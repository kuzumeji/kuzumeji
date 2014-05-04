// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.domain;
import com.kuzumeji.framework.standard.component.AbstractValueObject;
/**
 * JAASユーザ検索条件
 * @author nilcy
 */
public class JaasUserFilter extends AbstractValueObject<JaasUserFilter> {
    /** 識別番号 */
    private static final long serialVersionUID = -985653761854953733L;
    /** ユーザ名 */
    private final String userName;
    /**
     * コンストラクタ
     * @param userName {@link #userName}
     */
    public JaasUserFilter(final String userName) {
        this.userName = userName;
    }
    /**
     * {@link #userName} の取得
     * @return {@link #userName}
     */
    public final String getUserName() {
        return userName;
    }
}
