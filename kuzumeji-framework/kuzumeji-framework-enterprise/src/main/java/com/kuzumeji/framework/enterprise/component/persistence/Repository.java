// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Collection;
import java.util.Map;
/**
 * リポジトリI/F
 * @param <P> エンティティ型
 * @author nilcy
 */
public interface Repository<P extends Persistable> {
    /**
     * エンティティの保存
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entity エンティティ
     * @return 保存後エンティティ
     */
    <S extends P> S save(S entity);
    /**
     * エンティティの保存
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entities エンティティ
     * @return 保存後エンティティ
     */
    <S extends P> Collection<S> save(Iterable<S> entities);
    /**
     * エンティティのID検索
     * @param id ID
     * @return 該当エンティティ
     */
    P find(Object id);
    /**
     * エンティティの単一検索
     * @param name クエリ名
     * @param filter クエリ条件
     * @return 該当エンティティ
     */
    P findOne(final String name, final Map<String, Object> filter);
    /**
     * エンティティの複数検索
     * @param filter 検索条件
     * @return 該当エンティティ集合
     */
    Collection<P> findMany(Object filter);
    /**
     * エンティティの削除
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entity エンティティ
     */
    <S extends P> void delete(S entity);
    /**
     * エンティティの削除
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entities エンティティ
     */
    <S extends P> void delete(Iterable<S> entities);
    /**
     * データベースへ反映
     */
    void flush();
}
