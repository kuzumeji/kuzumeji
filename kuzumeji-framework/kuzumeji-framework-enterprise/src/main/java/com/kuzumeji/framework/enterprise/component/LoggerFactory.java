// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
/**
 * ロガーのファクトリー
 * <dl>
 * <dt>使用条件
 * <dd><code>@Inject Logger log;</code>のようにDIで使用すること。
 * </dl>
 * @author nilcy
 */
public class LoggerFactory {
    /** コンストラクタ */
    public LoggerFactory() {
    }
    /**
     * ロガーの作成
     * @param point 注入ポイント
     * @return ロガー
     */
    @Produces
    @Default
    public Logger createLogger(final InjectionPoint point) {
        return org.slf4j.LoggerFactory.getLogger(point.getMember().getDeclaringClass());
    }
}
