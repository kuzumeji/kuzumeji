// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see StandardException
 * @author nilcy
 */
@SuppressWarnings("all")
public class StandardExceptionTest {
    @Test
    public final void testStandardException() {
        assertThat(new StandardException(), is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionString() {
        assertThat(new StandardException("testee"), is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionThrowable() {
        assertThat(new StandardException(new UnsupportedOperationException()), is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionStringThrowable() {
        assertThat(new StandardException("testee", new UnsupportedOperationException()),
            is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionStringThrowableBooleanBoolean() {
        assertThat(
            new StandardException("testee", new UnsupportedOperationException(), true, true),
            is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionStringObjectArray() {
        assertThat(new StandardException("ERR_KSF_UK", "name=日本,key=国番号"), is(not(nullValue())));
    }
    @Test
    public final void testStandardExceptionMapOfStringObject() {
        assertThat(
            new StandardException(
                new StandardException("ERR_KSF_UK", "name=日本,key=国番号").getMessageMap()),
            is(not(nullValue())));
    }
    /**
     * Test method for
     * {@link com.kuzumeji.framework.standard.component.StandardException#getMessageMap()}.
     */
    @Test
    public final void testGetMessageMap() {
        assertThat(new StandardException().getMessageMap(), is(not(nullValue())));
    }
    @Test
    public final void testGetApplicationMessage() {
        assertThat(new StandardException("ERR_KFS_UK", "name=日本,key=国番号").getApplicationMessage(),
            is("一意キー制約の違反です。[name=日本,key=国番号]"));
    }
}
