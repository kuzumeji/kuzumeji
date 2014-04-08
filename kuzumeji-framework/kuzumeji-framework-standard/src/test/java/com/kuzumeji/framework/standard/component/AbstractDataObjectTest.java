// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @see AbstractDataObject
 * @author nilcy
 */
@SuppressWarnings("all")
public class AbstractDataObjectTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDataObjectTest.class);
    @Test
    public void test() {
        final TesteeDataObject testee01 = new TesteeDataObject("foo#01");
        final TesteeDataObject testee02 = new TesteeDataObject("foo#02");
        assertThat(testee01, is(not(testee02)));
        LOG.debug("testee01 : {}", testee01);
    }
    private class TesteeDataObject extends AbstractDataObject<TesteeDataObject> {
        /** 識別番号 */
        private static final long serialVersionUID = 7533112117543810098L;
        /** foo */
        private String foo;
        /**
         * コンストラクタ
         * @param foo {@link #foo}
         */
        public TesteeDataObject(final String foo) {
            this.foo = foo;
        }
        /**
         * {@link #foo} の取得
         * @return {@link #foo}
         */
        public String getFoo() {
            return foo;
        }
        /**
         * {@link #foo} の設定
         * @param foo {@link #foo}
         */
        public void setFoo(final String foo) {
            this.foo = foo;
        }
    }
}
