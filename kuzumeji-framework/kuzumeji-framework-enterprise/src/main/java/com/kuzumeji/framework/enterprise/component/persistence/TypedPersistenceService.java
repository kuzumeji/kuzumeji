// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.io.Serializable;
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
 * @param <PE> 基点エンティティ型
 * @param <ID> 識別子オブジェクト型
 * @author nilcy
 */
public interface TypedPersistenceService<PE extends Persistable<ID>, ID extends Serializable>
    extends PersistenceService {
    /**
     * 新規エンティティ管理
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"新規"から"管理"の状態になること。(DB同期は別途)
     * </dl>
     * @param <SE> エンティティ型
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#persist(Object)
     */
    <SE extends PE> void persist(SE entity);
    /**
     * 分離エンティティ管理
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"分離"から"管理"の状態になること。(DB同期は別途)
     * </dl>
     * @param <SE> エンティティ型
     * @param entity エンティティ
     * @return 更新後エンティティ
     * @see javax.persistence.EntityManager#merge(Object)
     */
    <SE extends PE> SE merge(SE entity);
    /**
     * 管理エンティティ削除
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"管理"から"削除"の状態になること。(DB同期は別途)
     * </dl>
     * @param <SE> エンティティ型
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#remove(Object)
     */
    <SE extends PE> void remove(SE entity);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティが検索対象のとき使用すること。</li>
     * </ol>
     * </dl>
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see #find(Class, Serializable)
     */
    PE find(ID id);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object)
     */
    <SE extends PE> SE find(Class<SE> entityClass, ID id);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティが検索対象のとき、プロパティを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param id IDオブジェクト
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, Map)
     */
    PE find(ID id, Map<String, Object> properties);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, Map)
     */
    <SE extends PE> SE find(Class<SE> entityClass, ID id, Map<String, Object> properties);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティが検索対象のとき、排他モードを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @return 該当エンティティ
     * @see #find(Class, Serializable, LockModeType)
     */
    PE find(ID id, LockModeType lockMode);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, LockModeType)
     */
    <SE extends PE> SE find(Class<SE> entityClass, ID id, LockModeType lockMode);
    /**
     * 管理エンティティID検索
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティを検索して、永続コンテキスト中で"管理"の状態になること。</li>
     * <li>基点エンティティが検索対象のとき、排他モードとプロパティを指定するとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see #find(Class, Serializable, LockModeType, Map)
     */
    PE find(ID id, LockModeType lockMode, Map<String, Object> properties);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#find(Class, Object, LockModeType, Map)
     */
    <SE extends PE> SE find(Class<SE> entityClass, ID id, LockModeType lockMode,
        Map<String, Object> properties);
    /**
     * エンティティ参照の取得
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキスト中またはDBからエンティティ参照を検索すること。</li>
     * <li>管理エンティティへ参照を設定するがエンティティ内容が必要ないとき、使用する。</li>
     * <li>基点エンティティが検索対象のとき、使用すること。</li>
     * </ol>
     * </dl>
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see #getReference(Class, Serializable)
     */
    PE getReference(ID id);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス(基点のサブクラス)
     * @param id IDオブジェクト
     * @return 該当エンティティ
     * @see javax.persistence.EntityManager#getReference(Class, Object)
     */
    <SE extends PE> SE getReference(Class<SE> entityClass, ID id);
    /**
     * 管理エンティティ排他
     * <dl>
     * <dt>使用条件
     * <dd>管理エンティティが排他されること。(排他モードは {@link LockModeType} を参照)
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @see javax.persistence.EntityManager#lock(Object, LockModeType)
     */
    <SE extends PE> void lock(SE entity, LockModeType lockMode);
    /**
     * 管理エンティティ排他
     * <dl>
     * <dt>使用条件
     * <dd>(プロパティ指定で)管理エンティティが排他されること。(排他モードは {@link LockModeType} を参照)
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#lock(Object, LockModeType, Map)
     */
    <SE extends PE> void lock(SE entity, LockModeType lockMode, Map<String, Object> properties);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>管理エンティティが更新されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#refresh(Object)
     */
    <SE extends PE> void refresh(SE entity);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(プロパティ指定で)管理エンティティが更新されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#refresh(Object, Map)
     */
    <SE extends PE> void refresh(SE entity, Map<String, Object> properties);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(排他モード指定で)管理エンティティが更新されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @see javax.persistence.EntityManager#refresh(Object, LockModeType)
     */
    <SE extends PE> void refresh(SE entity, LockModeType lockMode);
    /**
     * 管理エンティティ更新
     * <dl>
     * <dt>使用条件
     * <dd>(排他モード/プロパティ指定で)管理エンティティが更新されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @param lockMode 排他モード
     * @param properties 標準/固有プロパティ(ヒント含む)
     * @see javax.persistence.EntityManager#refresh(Object, LockModeType, Map)
     */
    <SE extends PE> void refresh(SE entity, LockModeType lockMode, Map<String, Object> properties);
    /**
     * 管理エンティティ分離
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中で"管理"から"分離"の状態になること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @see javax.persistence.EntityManager#detach(Object)
     */
    <SE extends PE> void detach(SE entity);
    /**
     * 管理エンティティ含有
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティが、永続コンテキスト中に含有するか確認されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @return エンティティ含有
     * @see javax.persistence.EntityManager#contains(Object)
     */
    <SE extends PE> boolean contains(SE entity);
    /**
     * 排他モードの取得
     * <dl>
     * <dt>使用条件
     * <dd>該当エンティティの排他モードが取得されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entity エンティティ
     * @return 排他モード
     * @see javax.persistence.EntityManager#getLockMode(Object)
     */
    <SE extends PE> LockModeType getLockMode(SE entity);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param queryObject クエリオブジェクト
     * @return タイプクエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaQuery)
     */
    <SE extends PE> TypedQuery<SE> createQuery(CriteriaQuery<SE> queryObject);
    /**
     * 更新クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>更新クエリオブジェクトを指定して、更新クエリが作成されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param updateQueryObject 更新クエリオブジェクト
     * @return クエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaUpdate)
     */
    <SE extends PE> Query createQuery(CriteriaUpdate<SE> updateQueryObject);
    /**
     * 削除クエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>削除クエリオブジェクトを指定して、削除クエリが作成されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param deleteQueryObject 削除クエリオブジェクト
     * @return クエリ
     * @see javax.persistence.EntityManager#createQuery(CriteriaDelete)
     */
    <SE extends PE> Query createQuery(CriteriaDelete<SE> deleteQueryObject);
    /**
     * タイプクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>JPQL文と結果クラスを指定して、タイプクエリが作成されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param jpql JPQL文
     * @param resultClass 結果クラス
     * @return タイプクエリ
     * @see javax.persistence.EntityManager#createQuery(String, Class)
     */
    <SE extends PE> TypedQuery<SE> createQuery(String jpql, Class<SE> resultClass);
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
    TypedQuery<PE> createNamedQuery(String name);
    /**
     * タイプクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>クエリ名と結果クラスを指定して、タイプクエリが作成されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param name クエリ名
     * @param resultClass 結果クラス
     * @return クエリ
     * @see javax.persistence.EntityManager#createNamedQuery(String, Class)
     */
    <SE extends PE> TypedQuery<SE> createNamedQuery(String name, Class<SE> resultClass);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param sql SQL文
     * @param resultClass 結果クラス
     * @return クエリ
     * @see javax.persistence.EntityManager#createNativeQuery(String, Class)
     */
    <SE extends PE> Query createNativeQuery(String sql, Class<SE> resultClass);
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
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス
     * @return JPAプロバイダ指定クラス
     * @see javax.persistence.EntityManager#unwrap(Class)
     */
    <SE extends PE> SE unwrap(Class<SE> entityClass);
    /**
     * エンティティグラフ作成
     * <dl>
     * <dt>使用条件
     * <dd>エンティティグラフクラスを指定して、エンティティグラフが作成されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param rootType エンティティグラフクラス
     * @return エンティティグラフ
     * @see javax.persistence.EntityManager#createEntityGraph(Class)
     */
    <SE extends PE> EntityGraph<SE> createEntityGraph(Class<SE> rootType);
    /**
     * 複数エンティティグラフ取得
     * <dl>
     * <dt>使用条件
     * <dd>エンティティクラスを指定して、エンティティグラフ(複数)が取得されること。
     * </dl>
     * @param <SE> エンティティ型(基点のサブタイプ)
     * @param entityClass エンティティクラス
     * @return エンティティグラフ(複数)
     * @see javax.persistence.EntityManager#getEntityGraphs(Class)
     */
    <SE extends PE> Collection<EntityGraph<? super SE>> getEntityGraphs(Class<SE> entityClass);
}
