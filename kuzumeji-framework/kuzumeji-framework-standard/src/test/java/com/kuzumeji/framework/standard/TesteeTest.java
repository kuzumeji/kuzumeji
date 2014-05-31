// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
/**
 * @see Testee
 * @author nilcy
 */
@SuppressWarnings({ "javadoc", "static-method" })
public class TesteeTest {
    /** @see PropertyUtils#getSimpleProperty(Object, String) */
    @Test
    public void test() throws IllegalAccessException, InvocationTargetException,
        NoSuchMethodException {
        final Testee testee = new Testee("foo#0", "bar#0", "baz#0");
        assertThat(testee, is(not(nullValue())));
        assertThat((String) PropertyUtils.getSimpleProperty(testee, "foo"), is("foo#0"));
    }
}
