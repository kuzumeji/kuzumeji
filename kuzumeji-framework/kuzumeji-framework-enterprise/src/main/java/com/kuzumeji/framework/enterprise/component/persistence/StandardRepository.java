// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Collection;
/**
 * 標準リポジトリI/F
 * @param <P> 基点エンティティ型
 * @author nilcy
 */
public interface StandardRepository<P extends Persistable> extends SimpleRepository<P> {
    /**
     * 単一検索
     * @param name クエリ名
     * @param object 検索オブジェクト
     * @param fields 検索フィールド配列
     * @return 該当エンティティ
     * @throws PersistenceException 検索の失敗
     */
    P findOne(final String name, final P object, final String... fields)
        throws PersistenceException;
    /**
     * 複数検索
     * @param filter 検索条件
     * @return 該当エンティティ集合
     * @throws PersistenceException 検索の失敗
     */
    Collection<P> findMany(Object filter) throws PersistenceException;
}
