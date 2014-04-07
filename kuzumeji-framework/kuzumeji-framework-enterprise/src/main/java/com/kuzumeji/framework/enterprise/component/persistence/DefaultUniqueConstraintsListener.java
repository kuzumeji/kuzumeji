// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import com.kuzumeji.framework.enterprise.component.EnterpriseRuntimeException;
/**
 * 一意キー制約リスナー
 * @param <P> エンティティ型
 * @author nilcy
 */
public class DefaultUniqueConstraintsListener<P extends Persistable> implements
    UniqueConstraintsListener<P> {
    /** クエリ名 */
    private final String queryName;
    /** 制約違反キー */
    private final String errorKey;
    /** 制約フィールド配列 */
    private final String[] fields;
    /**
     * コンストラクタ
     * @param queryName {@link #queryName}
     * @param errorKey {@link #errorKey}
     * @param fields {@link #fields}
     */
    public DefaultUniqueConstraintsListener(final String queryName, final String errorKey,
        final String... fields) {
        this.queryName = queryName;
        this.errorKey = errorKey;
        this.fields = fields;
    }
    /** {@inheritDoc} */
    @Override
    public String queryName() {
        return queryName;
    }
    /** {@inheritDoc} */
    @Override
    public String errorKey() {
        return errorKey;
    }
    /** {@inheritDoc} */
    @Override
    public Object[] values(final P object) {
        final Collection<Object> values = new ArrayList<>();
        for (final String field : fields) {
            try {
                values.add(PropertyUtils.getSimpleProperty(object, field));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new EnterpriseRuntimeException(e.getLocalizedMessage());
            }
        }
        return values.toArray(new Object[values.size()]);
    }
    /** {@inheritDoc} */
    @Override
    public Map<String, Object> filter(final P object) {
        final Map<String, Object> filter = new HashMap<>();
        for (final String field : fields) {
            try {
                filter.put(field, PropertyUtils.getSimpleProperty(object, field));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new EnterpriseRuntimeException(e.getLocalizedMessage());
            }
        }
        return filter;
    }
}
