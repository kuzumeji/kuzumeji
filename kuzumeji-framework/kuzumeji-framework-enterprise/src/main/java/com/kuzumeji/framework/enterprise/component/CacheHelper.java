// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
/**
 * キャッシュヘルパー
 * @param <K> キー型
 * @param <V> バリュー型
 * @author nilcy
 */
public final class CacheHelper<K, V> {
    /** アプリケーションスコープのキャッシュ領域名 */
    public static final String APPLICATION_AREA = "application";
    /** セッションスコープのキャッシュ領域名 */
    public static final String SESSION_AREA = "session";
    /** カンバセーションスコープのキャッシュ領域名 */
    public static final String CONVERSATION_AREA = "conversation";
    /** リクエストスコープのキャッシュ領域名 */
    public static final String REQUEST_AREA = "request";
    /** キャッシュマネージャ */
    private final CacheManager manager;
    /** コンストラクタ */
    public CacheHelper() {
        manager = Caching.getCacheManager();
    }
    /**
     * コンストラクタ
     * @param name キャッシュ領域名
     */
    public CacheHelper(final String name) {
        manager = Caching.getCacheManager(name);
    }
    /**
     * キャッシュの取得
     * @param name キャッシュ名
     * @return キャッシュ
     */
    public Cache<K, V> getCache(final String name) {
        return manager.getCache(name);
    }
    /**
     * キャッシュの作成
     * @param name キャッシュ名
     * @return キャッシュ
     */
    public Cache<K, V> createCache(final String name) {
        return manager.<K, V> createCacheBuilder(name).build();
    }
}
