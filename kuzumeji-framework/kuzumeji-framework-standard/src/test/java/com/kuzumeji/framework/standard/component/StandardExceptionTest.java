// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
/**
 * @see StandardException
 * @author nilcy
 */
@SuppressWarnings("all")
public class StandardExceptionTest {
    @Test
    public final void test() {
        try {
            throw new StandardException();
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is(nullValue()));
        }
        try {
            throw new StandardException("testee");
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new StandardException(new UnsupportedOperationException());
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is("java.lang.UnsupportedOperationException"));
        }
        try {
            throw new StandardException("testee", new UnsupportedOperationException());
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new StandardException("testee", new UnsupportedOperationException(), true, true);
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new StandardException("Country_UK_code", "81");
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(), is("一意キー制約の違反です。国[国コード=81]"));
        }
        try {
            final Map<String, Object[]> messageMap = new LinkedHashMap<>();
            messageMap.put("Country_UK_code", new Object[] { "81" });
            messageMap.put("Country_UK_name", new Object[] { "Japan" });
            throw new StandardException(messageMap);
        } catch (final StandardException e) {
            assertThat(e.getApplicationMessage(),
                is("一意キー制約の違反です。国[国コード=81] 一意キー制約の違反です。国[国名=Japan]"));
        }
    }
}
