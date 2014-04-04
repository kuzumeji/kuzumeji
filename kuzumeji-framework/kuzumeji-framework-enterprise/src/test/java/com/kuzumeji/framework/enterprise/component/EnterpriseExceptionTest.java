// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.enterprise.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
/**
 * @see EnterpriseException
 * @author nilcy
 */
@SuppressWarnings("all")
public class EnterpriseExceptionTest {
    @Test
    public final void test() {
        try {
            throw new EnterpriseException();
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is(nullValue()));
        }
        try {
            throw new EnterpriseException("testee");
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseException(new UnsupportedOperationException());
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is("java.lang.UnsupportedOperationException"));
        }
        try {
            throw new EnterpriseException("testee", new UnsupportedOperationException());
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseException("testee", new UnsupportedOperationException(), true, true);
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseException("Country_UK_code", "81");
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(), is("一意キー制約の違反です。国[国コード=81]"));
        }
        try {
            final Map<String, Object[]> messageMap = new LinkedHashMap<>();
            messageMap.put("Country_UK_code", new Object[] { "81" });
            messageMap.put("Country_UK_name", new Object[] { "Japan" });
            throw new EnterpriseException(messageMap);
        } catch (final EnterpriseException e) {
            assertThat(e.getApplicationMessage(),
                is("一意キー制約の違反です。国[国コード=81] 一意キー制約の違反です。国[国名=Japan]"));
        }
    }
}
