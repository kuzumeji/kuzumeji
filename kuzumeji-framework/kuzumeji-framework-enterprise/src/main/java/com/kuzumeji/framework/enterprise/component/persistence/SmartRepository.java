// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.util.Collection;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
/**
 * 先進リポジトリI/F
 * @param <R> 基点エンティティ型
 * @author nilcy
 */
public interface SmartRepository<R extends Persistable> extends SimpleRepository<R> {
    /**
     * {@link #builder} の取得
     * @return {@link #builder}
     */
    CriteriaBuilder getBuilder();
    CriteriaQuery<R> query();
    <T> CriteriaQuery<T> query(final Class<T> entityClass);
    Root<R> root();
    <T> Root<T> root(final Class<T> entityClass);
    <T> TypedQuery<T> query(final CriteriaQuery<T> query, final int... range);
    <T> T findOne(final TypedQuery<T> query) throws PersistenceException;
    <T> Collection<T> findMany(final TypedQuery<T> query) throws PersistenceException;
}
