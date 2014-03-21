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
     * @param entity エンティティ
     * @return 保存後エンティティ
     * @throws PersistenceException 保存の失敗
     */
    P save(P entity) throws PersistenceException;
    /**
     * エンティティの保存
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entities エンティティ
     * @return 保存後エンティティ
     * @throws PersistenceException 保存の失敗
     */
    <S extends P> Collection<S> save(Iterable<S> entities) throws PersistenceException;
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
     * @throws PersistenceException 保存の失敗
     */
    <S extends P> void delete(S entity) throws PersistenceException;
    /**
     * エンティティの削除
     * @param <S> エンティティ型(サブタイプでも可能)
     * @param entities エンティティ
     * @throws PersistenceException 保存の失敗
     */
    <S extends P> void delete(Iterable<S> entities) throws PersistenceException;
    /**
     * データベースへ反映
     * @throws PersistenceException 保存の失敗
     */
    void flush() throws PersistenceException;
}
