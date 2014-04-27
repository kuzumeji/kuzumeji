// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence.bak;
import java.util.Map;
import com.kuzumeji.framework.enterprise.component.persistence.Persistable;
/**
 * 一意キー制約リスナーI/F
 * @param <P> エンティティ型
 * @author nilcy
 */
public interface UniqueConstraintsListener<P extends Persistable> {
    /**
     * クエリ名の取得
     * @return クエリ名
     */
    String queryName();
    /**
     * 制約条件の作成
     * @param object 対象オブジェクト
     * @return 制約条件
     */
    Map<String, Object> filter(P object);
    /**
     * 制約違反キーの取得
     * @return 制約違反キー
     */
    String errorKey();
    /**
     * 制約フィールド配列の取得
     * @param object 対象オブジェクト
     * @return 制約フィールド配列
     */
    Object[] values(P object);
}
