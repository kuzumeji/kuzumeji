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
/**
 * @see PropertiesHelper
 * @author nilcy
 */
@SuppressWarnings("all")
public class PropertiesHelperTest {
    @Test
    public final void testSimple() throws ConfigurationException {
        final PropertiesHelper testee = new PropertiesHelper("usergui.properties");
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.getText("colors.background"), is("#FFFFFF"));
        assertThat(testee.getText("colors.foreground"), is("#000080"));
        assertThat(
            new Dimension(testee.getNumeric("window.width"), testee.getNumeric("window.height")),
            is(not(nullValue())));
        testee.setProperty("colors.foreground", "#000000");
        assertThat(testee.getText("colors.foreground"), is("#000000"));
        // testee.save();
        assertThat(testee.getText("key"), is("This \n string \t contains , escaped \\ characters"));
    }
    @Test
    public final void testVariable() throws ConfigurationException {
        final PropertiesHelper testee = new PropertiesHelper("application.properties");
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.getText("application.title"), is("Kuzumeji Framework 0.1.0-SNAPSHOT"));
    }
    @Test
    public final void testMessage() throws ConfigurationException {
        final PropertiesHelper testee = new PropertiesHelper("message.properties");
        assertThat(testee, is(not(nullValue())));
        assertThat(testee.getText("ERR_KFS_UK", "name=日本,key=国番号"),
            is("一意キー制約の違反です。[name=日本,key=国番号]"));
    }
}
