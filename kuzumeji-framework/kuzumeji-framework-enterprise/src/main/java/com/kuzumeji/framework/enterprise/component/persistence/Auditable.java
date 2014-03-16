// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.io.Serializable;
import java.util.Date;
/**
 * 監査可能エンティティI/F
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>監査可能エンティティは本I/Fを実装すること。</li>
 * <li>{@link Versionable 版管理エンティティI/F} を実装すること。</li>
 * </ol>
 * </dl>
 * @param <UO> 利用者オブジェクト型
 * @param <ID> 識別子オブジェクト型
 * @author nilcy
 */
public interface Auditable<UO extends Serializable, ID extends Serializable> extends
    Versionable<ID> {
    /**
     * 作成者の取得
     * @return 作成者
     */
    UO getCreatedBy();
    /**
     * 作成者の設定
     * @param createdBy 作成者
     */
    void setCreatedBy(UO createdBy);
    /**
     * 作成日時の取得
     * @return 作成日時の取得
     */
    Date getCreatedDate();
    /**
     * 作成日時の設定
     * @param createdDate 作成日時
     */
    void setCreatedDate(Date createdDate);
    /**
     * 最終更新者の取得
     * @return 最終更新者
     */
    UO getLastModifiedBy();
    /**
     * 最終更新者の設定
     * @param lastModifiedBy 最終更新者
     */
    void setLastModifiedBy(UO lastModifiedBy);
    /**
     * 最終更新日時の取得
     * @return 最終更新日時
     */
    Date getLastModifiedDate();
    /**
     * 最終更新日時の設定
     * @param lastModifiedDate 最終更新日時
     */
    void setLastModifiedDate(Date lastModifiedDate);
}
