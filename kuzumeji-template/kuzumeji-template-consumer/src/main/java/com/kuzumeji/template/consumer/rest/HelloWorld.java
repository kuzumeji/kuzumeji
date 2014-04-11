// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.consumer.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 * Root resource (exposed at "helloworld" path)
 * @author nilcy
 */
@Path("helloworld")
public class HelloWorld {
    /** コンストラクタ */
    public HelloWorld() {
    }
    @GET
    @Produces("text/plain")
    public String getHello() {
        return "Hello World!";
    }
}
