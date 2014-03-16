// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component.persistence;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 監査可能エンティティ
 * <dl>
 * <dt>使用条件
 * <dd>
 * <ol>
 * <li>{@link Auditable 監査可能エンティティI/F} が実装されること。</li>
 * <li>{@link AbstractVersionable 版管理エンティティ} が継承されること。</li>
 * <li>作成日時と最終更新日時がJPAライフサイクルにあわせて更新されること。</li>
 * </ol>
 * </dl>
 * @param <AE> 監査可能エンティティ型
 * @author nilcy
 */
@MappedSuperclass
public abstract class AbstractAuditable<AE extends AbstractAuditable<AE>> extends
    AbstractVersionable<AE> implements Auditable<Long, BigDecimal> {
    /** 識別番号 */
    private static final long serialVersionUID = 6992851617530122569L;
    /** 作成者(ID) */
    @Column(name = "createdBy", nullable = true, insertable = true, updatable = false)
    private Long createdBy;
    /** 作成日時 */
    @Column(name = "createdDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    /** 最終更新者(ID) */
    @Column(name = "lastModifiedBy", nullable = true, insertable = true, updatable = true)
    private Long lastModifiedBy;
    /** 最終更新日時 */
    @Column(name = "lastModifiedDate", nullable = true, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    /** コンストラクタ */
    public AbstractAuditable() {
    }
    @Override
    public final Long getCreatedBy() {
        return createdBy;
    }
    @Override
    public final void setCreatedBy(final Long createdBy) {
        this.createdBy = createdBy;
    }
    @Override
    public final Date getCreatedDate() {
        return createdDate;
    }
    @Override
    public final void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public final Long getLastModifiedBy() {
        return lastModifiedBy;
    }
    @Override
    public final void setLastModifiedBy(final Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    @Override
    public final Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    @Override
    public final void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    /**
     * 登録前コールバック
     * <dl>
     * <dt>使用条件
     * <dd>作成日時と最終更新日時へ現在日時を設定すること。
     * </dl>
     */
    @PrePersist
    private void prePersist() {
        final Date now = new Date();
        createdDate = now;
        lastModifiedDate = now;
    }
    /**
     * 更新前コールバック
     * <dl>
     * <dt>使用条件
     * <dd>最終更新日時へ現在日時を設定すること。
     * </dl>
     */
    @PreUpdate
    private void preUpdate() {
        lastModifiedDate = new Date();
    }
}
