// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Map;
/**
 * UK制約条件I/F
 * @param <T> 対象オブジェクト型
 * @author nilcy
 */
public interface UniqueFilterFactory<T> {
    /**
     * UK制約条件の作成
     * @param object 対象オブジェクト
     * @return UK制約条件
     */
    Map<String, Object> create(T object);
    /**
     * UK制約違反オブジェクト配列の取得
     * @param object 対象オブジェクト
     * @return UK制約違反オブジェクト配列
     */
    Object[] toArray(T object);
}
