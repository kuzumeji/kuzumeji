// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
/**
 * <dl>
 * <dt>使用条件
 * <dd>TODO 各操作前後の不変条件を表明すること。
 * </dl>
 * @author nilcy
 */
public class DefaultSmartRepositoryListener<R extends Persistable, F> implements
    SmartRepositoryListener<R, F> {
    /** {@inheritDoc} */
    @Override
    public CriteriaQuery<R> query(final CriteriaBuilder builder, final CriteriaQuery<R> query,
        final Root<R> root, final F filter) {
        query.select(root);
        return query;
    }
}
