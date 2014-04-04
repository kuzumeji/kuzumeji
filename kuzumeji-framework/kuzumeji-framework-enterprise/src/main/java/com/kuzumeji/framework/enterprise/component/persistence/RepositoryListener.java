// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Map;
/**
 * リポジトリリスナーI/F
 * @param <P> エンティティ型
 * @author nilcy
 */
public interface RepositoryListener<P extends Persistable> {
    /**
     * UK制約違反キーの取得
     * @return UK制約違反キー
     */
    String uniqueKey();
    /**
     * UK制約違反オブジェクト配列の取得
     * @param object 対象オブジェクト
     * @return UK制約違反オブジェクト配列
     */
    Object[] uniqueValues(P object);
    /**
     * UK制約条件の作成
     * @param object 対象オブジェクト
     * @return UK制約条件
     */
    Map<String, Object> uniqueFilter(P object);
}
