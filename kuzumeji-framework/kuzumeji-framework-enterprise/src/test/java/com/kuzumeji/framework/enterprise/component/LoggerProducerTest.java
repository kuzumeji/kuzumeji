// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import com.kuzumeji.framework.testing.ArchiveFactory;
/**
 * @see LoggerProducer
 * @author nilcy
 */
@RunWith(Arquillian.class)
@SuppressWarnings("all")
public class LoggerProducerTest {
    @Inject
    private Logger testee;
    @Deployment
    public static JavaArchive deploy() {
        return ArchiveFactory.createJar();
    }
    @Test
    public final void test() {
        assertThat(testee, is(not(nullValue())));
        testee.trace("[追跡] {},{},{}", "T1", "T2", "T3");
        testee.debug("[検査] {},{},{}", "D1", "D2", "D3");
        testee.info("[情報] {},{},{}", "I1", "I2", "I3");
        testee.warn("[警告] {},{},{}", "W1", "W2", "W3");
        testee.error("[例外] {},{},{}", "E1", "E2", "E3");
    }
}
