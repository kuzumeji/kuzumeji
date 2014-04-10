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
 * @see CacheArea
 * @author nilcy
 */
public class CacheAreaTest {
    @Test
    public void test() {
        assertThat(CacheArea.APPLICATION.name().toLowerCase(), is("application"));
        assertThat(CacheArea.SESSION.name().toLowerCase(), is("session"));
        assertThat(CacheArea.CONVERSATION.name().toLowerCase(), is("conversation"));
        assertThat(CacheArea.REQUEST.name().toLowerCase(), is("request"));
    }
}