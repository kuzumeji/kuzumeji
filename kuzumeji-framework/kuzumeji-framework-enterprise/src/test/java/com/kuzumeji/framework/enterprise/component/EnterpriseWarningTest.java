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
 * @see EnterpriseWarning
 * @author nilcy
 */
@SuppressWarnings("all")
public class EnterpriseWarningTest {
    /**
     * @see EnterpriseWarning#EnterpriseWarning()
     * @see EnterpriseWarning#EnterpriseWarning(String)
     * @see EnterpriseWarning#EnterpriseWarning(Throwable)
     * @see EnterpriseWarning#EnterpriseWarning(String, Throwable)
     * @see EnterpriseWarning#EnterpriseWarning(String, Throwable, boolean, boolean)
     * @see EnterpriseWarning#EnterpriseWarning(String, Object...)
     * @see EnterpriseWarning#EnterpriseWarning(Map)
     * @see EnterpriseWarning#getApplicationMessage()
     */
    @Test
    public final void test() {
        try {
            throw new EnterpriseWarning();
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is(nullValue()));
        }
        try {
            throw new EnterpriseWarning("testee");
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseWarning(new UnsupportedOperationException());
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is("java.lang.UnsupportedOperationException"));
        }
        try {
            throw new EnterpriseWarning("testee", new UnsupportedOperationException());
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseWarning("testee", new UnsupportedOperationException(), true, true);
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is("testee"));
        }
        try {
            throw new EnterpriseWarning("Country.UK_code", "81");
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(), is("一意キー制約の違反です。国[国コード=81]"));
        }
        try {
            final Map<String, Object[]> messageMap = new LinkedHashMap<>();
            messageMap.put("Country.UK_code", new Object[] { "81" });
            messageMap.put("Country.UK_name", new Object[] { "Japan" });
            throw new EnterpriseWarning(messageMap);
        } catch (final EnterpriseWarning e) {
            assertThat(e.getApplicationMessage(),
                is("一意キー制約の違反です。国[国コード=81]\t一意キー制約の違反です。国[国名=Japan]"));
        }
    }
}
