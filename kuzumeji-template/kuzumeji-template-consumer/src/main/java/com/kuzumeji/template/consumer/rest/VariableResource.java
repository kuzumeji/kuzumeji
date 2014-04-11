// ----------------------------------------------------------------------------
// Copyright (C) Kuzumeji Evolution Laboratory. All rights reserved.
// GNU GENERAL PUBLIC LICENSE Version 3, 29 June 2007
// http://www.gnu.org/licenses/gpl-3.0-standalone.html
// ----------------------------------------------------------------------------
package com.kuzumeji.template.consumer.rest;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * 変数リソース
 * @author nilcy
 */
@Path("/variables/")
public class VariableResource {
    /** 変数 */
    private final Collection<Variable> variables = new ArrayList<Variable>();
    /** コンストラクタ */
    public VariableResource() {
    }
    /** 初期処理 */
    @PostConstruct
    public void postConstruct() {
        for (int i = 0; i < 10; ++i) {
            variables.add(new Variable(String.format("foo#%02d", i), String.format("bar#%02d", i),
                String.format("baz#%02d", i)));
        }
    }
    /**
     * 変数の取得
     * @return 変数
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Variable> getVariables() {
        return variables;
    }
}
