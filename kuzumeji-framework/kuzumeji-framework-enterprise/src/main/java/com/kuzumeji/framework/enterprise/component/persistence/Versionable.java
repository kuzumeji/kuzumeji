// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.io.Serializable;
/**
 * 版管理エンティティI/F
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>版管理エンティティは本I/Fを実装すること。</li>
 * <li>{@link Persistable 永続可能エンティティI/F} を実装すること。</li>
 * </ol>
 * </dl>
 * @param <I> 識別子オブジェクト型
 * @author nilcy
 */
public interface Versionable<I extends Serializable> extends Persistable<I> {
    /**
     * 版数の取得
     * @return 版数
     */
    Long getVersion();
    /**
     * 版数の設定
     * @param version 版数
     */
    void setVersion(Long version);
}
