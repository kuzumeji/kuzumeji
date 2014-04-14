// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @see SystemUtils
 * @author nilcy
 */
public class SystemUtilsTest {
    /** ロガー */
    private static final Logger LOG = LoggerFactory.getLogger(SystemUtilsTest.class);
    /** #see {@link SystemUtils#JAVA_IO_TMPDIR} */
    @Test
    public void test() {
        LOG.debug("{}", SystemUtils.JAVA_IO_TMPDIR);
    }
}
