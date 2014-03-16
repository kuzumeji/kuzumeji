// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Map;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import com.kuzumeji.framework.standard.component.Service;
/**
 * データ永続化サービスI/F
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>データ永続化サービスは本I/Fを実装すること。</li>
 * <li>{@link Service サービスI/F} を実装すること。</li>
 * </ol>
 * </dl>
 * @author nilcy
 */
public interface PersistenceService extends Service {
    /**
     * 管理エンティティ同期
     * <dl>
     * <dt>使用条件
     * <dd>管理エンティティがDBへ同期されること。
     * </dl>
     * @see javax.persistence.EntityManager#flush()
     */
    void flush();
    /**
     * 同期モードの設定
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>デフォルト: {@link FlushModeType#AUTO} のときクエリ実行で同期する。</li>
     * <li>{@link FlushModeType#COMMIT} のときトランザクション確定(commit)で同期する。</li>
     * </ol>
     * </dl>
     * @param flushMode 同期モード
     * @see javax.persistence.EntityManager#setFlushMode(FlushModeType)
     */
    void setFlushMode(FlushModeType flushMode);
    /**
     * 同期モードの取得
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>デフォルト: {@link FlushModeType#AUTO} のときクエリ実行で同期する。</li>
     * <li>{@link FlushModeType#COMMIT} のときトランザクション確定(commit)で同期する。</li>
     * </ol>
     * </dl>
     * @return 同期モード
     * @see javax.persistence.EntityManager#getFlushMode()
     */
    FlushModeType getFlushMode();
    /**
     * 永続コンテキスト消去
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>永続コンテキストを消去し、すべての管理エンティティが分離されること。(DB同期は破棄)</li>
     * </ol>
     * </dl>
     * @see javax.persistence.EntityManager#clear()
     */
    void clear();
    /**
     * プロパティの設定
     * <dl>
     * <dt>使用条件
     * <dd>該当プロパティが設定されること。
     * </dl>
     * @param name プロパティ名
     * @param value プロパティ値
     * @see javax.persistence.EntityManager#setProperty(String, Object)
     */
    void setProperty(String name, Object value);
    /**
     * プロパティマップの取得
     * <dl>
     * <dt>使用条件
     * <dd>プロパティマップが取得されること。
     * </dl>
     * @return プロパティマップ
     * @see javax.persistence.EntityManager#getProperties()
     */
    Map<String, Object> getProperties();
    /**
     * JTAトランザクション参加
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>トランザクションタイプが、"JTA"であること。</li>
     * <li>アプリケーション管理のトランザクションが、JTAトランザクションへ参加されること。</li>
     * </ol>
     * </dl>
     * @see javax.persistence.EntityManager#joinTransaction()
     */
    void joinTransaction();
    /**
     * JTAトランザクション参加チェック
     * <dl>
     * <dt>使用条件
     * <dd>JTAトランザクションへ参加しているかチェックされること。
     * </dl>
     * @return トランザクション参加の有無
     * @see javax.persistence.EntityManager#isJoinedToTransaction()
     */
    boolean isJoinedToTransaction();
    /**
     * JPAプロバイダ用オブジェクトの取得
     * <dl>
     * <dt>使用条件
     * <dd>JPAプロバイダ用オブジェクトが取得されること。
     * </dl>
     * @return JPAプロバイダ用オブジェクト
     * @see javax.persistence.EntityManager#getDelegate()
     */
    Object getDelegate();
    /**
     * エンティティマネージャ終了
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>トランザクションタイプが、"RESOURCE_LOCAL"であること。</li>
     * <li>アプリケーション管理のエンティティマネージャが終了(クローズ)されること。</li>
     * </ol>
     * </dl>
     * @see javax.persistence.EntityManager#close()
     */
    void close();
    /**
     * エンティティマネージャ状況チェック
     * <dl>
     * <dt>使用条件
     * <dd>エンティティマネージャが開始(オープン)しているかチェックされること。
     * </dl>
     * @return エンティティマネージャが開始しているときTRUE
     * @see javax.persistence.EntityManager#isOpen()
     */
    boolean isOpen();
    /**
     * エンティティトランザクション取得
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>トランザクションタイプが、"RESOURCE_LOCAL"であること。</li>
     * <li>リソースレベルのエンティティトランザクションが取得されること。</li>
     * </ol>
     * </dl>
     * @return リソースレベルのエンティティトランザクション
     * @see javax.persistence.EntityManager#getTransaction()
     */
    EntityTransaction getTransaction();
    /**
     * エンティティマネージャファクトリ取得
     * <dl>
     * <dt>使用条件
     * <dd>エンティティマネージャファクトリが取得されること。
     * </dl>
     * @return エンティティマネージャファクトリ
     * @see javax.persistence.EntityManager#getEntityManagerFactory()
     */
    javax.persistence.EntityManagerFactory getEntityManagerFactory();
    /**
     * クライテリアビルダー取得
     * <dl>
     * <dt>使用条件
     * <dd>クライテリアビルダーが取得されること。
     * </dl>
     * @return クライテリアビルダー
     * @see javax.persistence.EntityManager#getCriteriaBuilder()
     */
    CriteriaBuilder getCriteriaBuilder();
    /**
     * メタモデル取得
     * <dl>
     * <dt>使用条件
     * <dd>メタモデルが取得されること。
     * </dl>
     * @return メタモデル
     * @see javax.persistence.EntityManager#getMetamodel()
     */
    Metamodel getMetamodel();
    /**
     * エンティティグラフ作成
     * <dl>
     * <dt>使用条件
     * <dd>エンティティグラフ名を指定して、エンティティグラフが作成されること。
     * </dl>
     * @param graphName エンティティグラフ名
     * @return エンティティグラフ
     * @see javax.persistence.EntityManager#createEntityGraph(String)
     */
    EntityGraph<?> createEntityGraph(String graphName);
    /**
     * エンティティグラフ取得
     * <dl>
     * <dt>使用条件
     * <dd>エンティティグラフ名を指定して、エンティティグラフが取得されること。
     * </dl>
     * @param graphName エンティティグラフ名
     * @return エンティティグラフ
     * @see javax.persistence.EntityManager#getEntityGraph(String)
     */
    EntityGraph<?> getEntityGraph(String graphName);
    /**
     * ストアドプロシージャクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>ストアドプロシージャクエリ名を指定して、ストアドプロシージャクエリが作成されること。
     * </dl>
     * @param name ストアドプロシージャクエリ名
     * @return ストアドプロシージャクエリ
     * @see javax.persistence.EntityManager#createNamedStoredProcedureQuery(String)
     */
    StoredProcedureQuery createNamedStoredProcedureQuery(String name);
    /**
     * ストアドプロシージャクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>ストアドプロシージャ名を指定して、ストアドプロシージャクエリが作成されること。
     * </dl>
     * @param procedureName ストアドプロシージャ名
     * @return ストアドプロシージャクエリ
     * @see javax.persistence.EntityManager#createStoredProcedureQuery(String)
     */
    StoredProcedureQuery createStoredProcedureQuery(String procedureName);
    /**
     * ストアドプロシージャクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>ストアドプロシージャ名と結果クラス(複数)を指定して、ストアドプロシージャクエリが作成されること。
     * </dl>
     * @param procedureName ストアドプロシージャ名
     * @param resultClasses 結果クラス(複数)
     * @return ストアドプロシージャクエリ
     * @see javax.persistence.EntityManager#createStoredProcedureQuery(String, Class...)
     */
    StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class<?>... resultClasses);
    /**
     * ストアドプロシージャクエリの作成
     * <dl>
     * <dt>使用条件
     * <dd>ストアドプロシージャ名と結果セットマッピング名(複数)を指定して、ストアドプロシージャクエリが作成されること。
     * </dl>
     * @param procedureName ストアドプロシージャ名
     * @param resultSetMappings 結果セットマッピング名(複数)
     * @return ストアドプロシージャクエリ
     * @see javax.persistence.EntityManager#createStoredProcedureQuery(String, String...)
     */
    StoredProcedureQuery createStoredProcedureQuery(String procedureName,
        String... resultSetMappings);
    /**
     * {@link javax.persistence.EntityManager エンティティマネージャ} の取得
     * <dl>
     * <dt>使用条件
     * <dd>
     * <ol>
     * <li>トランザクションタイプが、"RESOURCE_LOCAL"であること。</li>
     * </ol>
     * </dl>
     * @return {@link javax.persistence.EntityManager エンティティマネージャ}
     */
    EntityManager getManager();
}
