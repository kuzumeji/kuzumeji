// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @see EnterpriseRuntimeException
 * @author nilcy
 */
@SuppressWarnings("all")
public class EnterpriseRuntimeExceptionTest {
    @Test
    public final void test() {
        try {
            throw new EnterpriseRuntimeException();
        } catch (final EnterpriseRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is(nullValue()));
        }
        try {
            throw new EnterpriseRuntimeException("testee");
        } catch (final EnterpriseRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
        try {
            throw new EnterpriseRuntimeException(new UnsupportedOperationException());
        } catch (final EnterpriseRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("java.lang.UnsupportedOperationException"));
        }
        try {
            throw new EnterpriseRuntimeException("testee", new UnsupportedOperationException());
        } catch (final EnterpriseRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
        try {
            throw new EnterpriseRuntimeException("testee", new UnsupportedOperationException(),
                true, true);
        } catch (final EnterpriseRuntimeException e) {
            assertThat(e.getLocalizedMessage(), is("testee"));
        }
    }
}
