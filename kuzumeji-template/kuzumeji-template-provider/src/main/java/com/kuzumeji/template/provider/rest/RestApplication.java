// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.provider.rest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 * RESTアプリケーション
 * @author nilcy
 */
@ApplicationPath("/resources/*")
public class RestApplication extends Application {
    /** コンストラクタ */
    public RestApplication() {
    }
}
