// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @see Hello
 * @author nilcy
 */
@SuppressWarnings("static-method")
public class LoggingTest {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(LoggingTest.class);
    /**
     * @see Logger#trace(String, Object...)
     * @see Logger#debug(String, Object...)
     * @see Logger#info(String, Object...)
     * @see Logger#warn(String, Object...)
     * @see Logger#error(String, Object...)
     */
    @Test
    public void test() {
        LOG.trace("[追跡] {},{},{}", "T1", "T2", "T3");
        LOG.debug("[検査] {},{},{}", "D1", "D2", "D3");
        LOG.info("[情報] {},{},{}", "I1", "I2", "I3");
        LOG.warn("[警告] {},{},{}", "W1", "W2", "W3");
        LOG.error("[例外] {},{},{}", "E1", "E2", "E3");
    }
}
