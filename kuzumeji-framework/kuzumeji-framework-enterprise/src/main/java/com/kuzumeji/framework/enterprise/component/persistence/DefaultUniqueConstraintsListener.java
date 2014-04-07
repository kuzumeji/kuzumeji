// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import com.kuzumeji.framework.enterprise.component.EnterpriseRuntimeException;
/**
 * (デフォルト)一意キー制約リスナー
 * <dl>
 * <dt>使用条件
 * <dd>JPQLクエリ名、エンティティ項目、制約違反キーを指定した、一意キー制約リスナーとして使用すること。
 * </dl>
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
     * @param queryName {@link #queryName クエリ名}
     * @param errorKey {@link #errorKey 制約違反キー}
     * @param fields {@link #fields 制約フィールド配列}
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
    /**
     * {@inheritDoc}
     * <dl>
     * <dt>使用条件
     * <dd>制約フィールド分の対象オブジェクトを制約条件マップとして返却すること。
     * </dl>
     */
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
    /** {@inheritDoc} */
    @Override
    public String errorKey() {
        return errorKey;
    }
    /**
     * {@inheritDoc}
     * <dl>
     * <dt>使用条件
     * <dd>制約フィールド分の対象オブジェクト配列を返却すること。
     * </dl>
     */
    @Override
    public Object[] values(final P object) {
        final Collection<Object> values = filter(object).values();
        return values.toArray(new Object[values.size()]);
    }
}
