// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Collection;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
/**
 * 型付けデータ永続化サービスI/F
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>型付けデータ永続化サービスは本I/Fを実装すること。</li>
 * <li>{@link PersistenceService データ永続化サービスI/F} を実装すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public interface LegacyPersistenceService extends PersistenceService {
    /**
     * 新規エンティティ管理
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"新規"から"管理"の状態になること。(DB同期は別途)
     * </dl>
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#persist(Object)
     */
    void persist(Object entity);
    /**
     * 分離エンティティ管理
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"分離"から"管理"の状態になること。(DB同期は別途)
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @return 更新後エンティティ
     * @see javax.persistence.EntityManager#merge(Object)
     */
    <T> T merge(T entity);
    /**
     * 管理エンティティ削除
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"管理"から"削除"の状態になること。(DB同期は別途)
     * </dl>
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#remove(Object)
     */
    void remove(Object entity);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティのサブタイプを検索対象に指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object)
     */
    <T> T find(Class<T> entityClass, Object id);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティのサブタイプを検索対象に指定するとき、プロパティを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, Map)
     */
    <T> T find(Class<T> entityClass, Object id, Map<String, Object> properties);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティのサブタイプを検索対象に指定するとき、排他モードを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, LockModeType)
     */
    <T> T find(Class<T> entityClass, Object id, LockModeType lockMode);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティのサブタイプを検索対象に指定するとき、排他モードとプロパティを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, LockModeType, Map)
     */
    <T> T find(Class<T> entityClass, Object id, LockModeType lockMode,
        Map<String, Object> properties);
    /**
     * エンティティ参照の取得
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティ参照を検索すること。</li>
     * <li>管理エンティティへ参照を設定するがエンティティ内容が必要ないとき、使用する。</li>
     * <li>基点エンティティのサブタイプを検索対象に指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#getReference(Class, Object)
     */
    <T> T getReference(Class<T> entityClass, Object id);
    /**
     * 管理エンティティ排他
     * <dl>
     * <dt>使用条件
     * <dd>管理エンティティが排他されること。(排他モードは {@link LockModeType} を参照)
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @see javax.persistence.EntityManager#lock(Object, LockModeType)
     */
    <T> void lock(T entity, LockModeType lockMode);
    /**
     * 管理エンティティ排他
     * <dl>
     * <dt>使用条件
     * <dd>(プロパティ指定で)管理エンティティが排他されること。(排他モードは {@link LockModeType} を参照)
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#lock(Object, LockModeType, Map)
     */
    <T> void lock(T entity, LockModeType lockMode, Map<String, Object> properties);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>管理エンティティが更新されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#refresh(Object)
     */
    <T> void refresh(T entity);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(プロパティ指定で)管理エンティティが更新されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#refresh(Object, Map)
     */
    <T> void refresh(T entity, Map<String, Object> properties);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(排他モード指定で)管理エンティティが更新されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @see javax.persistence.EntityManager#refresh(Object, LockModeType)
     */
    <T> void refresh(T entity, LockModeType lockMode);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(排他モード/プロパティ指定で)管理エンティティが更新されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#refresh(Object, LockModeType, Map)
     */
    <T> void refresh(T entity, LockModeType lockMode, Map<String, Object> properties);
    /**
     * 管理エンティティ分離
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"管理"から"分離"の状態になること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#detach(Object)
     */
    <T> void detach(T entity);
    /**
     * 管理エンティティ含有
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中に含有するか確認されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @return エンティティ含有
     * @see javax.persistence.EntityManager#contains(Object)
     */
    <T> boolean contains(T entity);
    /**
     * 排他モードの取得
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティの排他モードが取得されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entity エンティティ
     * @return 排他モード
     * @see javax.persistence.EntityManager#getLockMode(Object)
     */
    <T> LockModeType getLockMode(T entity);
    /**
     * クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>JPQL文を指定して、クエリが作成されること。
     * </dl>
     * @param jpql JPQL文
     * @return クエリ
     * @see javax.persistence.EntityManager#createQuery(String)
     */
    Query createQuery(String jpql);
    /**
     * タイプクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>クエリオブジェクトを指定して、タイプクエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param queryObject クエリオブジェクト
     * @return タイプクエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaQuery)
     */
    <T> TypedQuery<T> createQuery(CriteriaQuery<T> queryObject);
    /**
     * 更新クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>更新クエリオブジェクトを指定して、更新クエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param updateQueryObject 更新クエリオブジェクト
     * @return クエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaUpdate)
     */
    <T> Query createQuery(CriteriaUpdate<T> updateQueryObject);
    /**
     * 削除クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>削除クエリオブジェクトを指定して、削除クエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param deleteQueryObject 削除クエリオブジェクト
     * @return クエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaDelete)
     */
    <T> Query createQuery(CriteriaDelete<T> deleteQueryObject);
    /**
     * タイプクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>JPQL文と結果クラスを指定して、タイプクエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param jpql JPQL文
     * @param resultClass 結果クラス
     * @return タイプクエリ
     * @see javax.persistence.EntityManager#createQuery(String, Class)
     */
    <T> TypedQuery<T> createQuery(String jpql, Class<T> resultClass);
    /**
     * クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>クエリ名を指定して、クエリが作成されること。
     * </dl>
     * @param name クエリ名
     * @return クエリ
     * @see javax.persistence.EntityManager#createNamedQuery(String)
     */
    Query createNamedQuery(String name);
    /**
     * タイプクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>クエリ名と結果クラスを指定して、タイプクエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param name クエリ名
     * @param resultClass 結果クラス
     * @return クエリ
     * @see javax.persistence.EntityManager#createNamedQuery(String, Class)
     */
    <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass);
    /**
     * クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>SQL文を指定して、クエリが作成されること。
     * </dl>
     * @param sql SQL文
     * @return クエリ
     * @see javax.persistence.EntityManager#createNativeQuery(String)
     */
    Query createNativeQuery(String sql);
    /**
     * クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>SQL文と結果クラスを指定して、クエリが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param sql SQL文
     * @param resultClass 結果クラス
     * @return クエリ
     * @see javax.persistence.EntityManager#createNativeQuery(String, Class)
     */
    <T> Query createNativeQuery(String sql, Class<T> resultClass);
    /**
     * クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>SQL文と結果セットマッピング名を指定して、クエリが作成されること。
     * </dl>
     * @param sql SQL文
     * @param resultSetMapping 結果セットマッピング名
     * @return クエリ
     * @see javax.persistence.EntityManager#createNativeQuery(String, String)
     */
    Query createNativeQuery(String sql, String resultSetMapping);
    /**
     * JPAプロバイダ固有クラスの取得
     * <dl>
     * <dt>使用条件
     * <dd>エンティティクラスとJPAプロバイダ指定クラスを指定して、JPAプロバイダ固有クラスが取得されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス
     * @return JPAプロバイダ指定クラス
     * @see javax.persistence.EntityManager#unwrap(Class)
     */
    <T> T unwrap(Class<T> entityClass);
    /**
     * エンティティグラフ作成
     * <dl>
     * <dt>使用条件
     * <dd>エンティティグラフクラスを指定して、エンティティグラフが作成されること。
     * </dl>
     * @param <T> エンティティ型
     * @param rootType エンティティグラフクラス
     * @return エンティティグラフ
     * @see javax.persistence.EntityManager#createEntityGraph(Class)
     */
    <T> EntityGraph<T> createEntityGraph(Class<T> rootType);
    /**
     * 複数エンティティグラフ取得
     * <dl>
     * <dt>使用条件
     * <dd>エンティティクラスを指定して、エンティティグラフ(複数)が取得されること。
     * </dl>
     * @param <T> エンティティ型
     * @param entityClass エンティティクラス
     * @return エンティティグラフ(複数)
     * @see javax.persistence.EntityManager#getEntityGraphs(Class)
     */
    <T> Collection<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass);
}
