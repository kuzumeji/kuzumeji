// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.framework.standard.component;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.awt.Dimension;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;
import com.kuzumeji.framework.testing.CoverageHelper;
/**
 * @see ConfigHelper
 * @author nilcy
 */
public class ConfigHelperTest {
    @Test
    public final void testSimple() throws ConfigurationException {
        CoverageHelper.privateConstructor(ConfigHelper.class);
        assertThat(ConfigHelper.MESSAGE_BASENAME, is("messages"));
        assertThat(ConfigHelper.ERROR_MESSAGE_BASENAME, is("error-messages"));
        assertThat(ConfigHelper.INET_ADDRESS_CHARSET, is("UTF-8"));
        assertThat(ConfigHelper.getText("colors.background"), is("#FFFFFF"));
        assertThat(ConfigHelper.getText("colors.foreground"), is("#000080"));
        assertThat(
            new Dimension(ConfigHelper.getNumeric("window.width"),
                ConfigHelper.getNumeric("window.height")), is(not(nullValue())));
        assertArrayEquals(ConfigHelper.getTexts("vars"), new Object[] { "foo", "bar", "baz" });
    }
}
