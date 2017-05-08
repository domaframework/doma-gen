/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.extension.gen.dialect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.doma.extension.gen.internal.util.AssertionUtil;

/**
 * 方言のレジストリです。
 * 
 * @author taedium
 * 
 */
public class GenDialectRegistry {

    /** 方言名をキー、方言を値とするマップ */
    protected final static Map<String, GenDialect> genDialectMap = Collections
            .synchronizedMap(new HashMap<String, GenDialect>());
    static {
        register(new StandardGenDialect());
        register(new HsqldbGenDialect());
        register(new H2GenDialect());
        register(new MysqlGenDialect());
        register(new PostgresGenDialect());
        register(new Oracle11GenDialect());
        register(new OracleGenDialect());
        register(new Db2GenDialect());
        register(new Mssql2008GenDialect());
        register(new MssqlGenDialect());
    }

    /**
     * 登録します。
     * 
     * @param dialect
     *            方言
     */
    public static void register(GenDialect dialect) {
        AssertionUtil.assertNotNull(dialect);
        genDialectMap.put(dialect.getName(), dialect);
    }

    /**
     * 検索します。
     * 
     * @param dialectName
     *            方言名
     * @return 方言、登録されていなければ {@code null}
     */
    public static GenDialect lookup(String dialectName) {
        return genDialectMap.get(dialectName);
    }

    /**
     * 登録されている方言名のリストを返します。
     * 
     * @return 登録されている方言名のリスト
     */
    public static List<String> getDialectNames() {
        return new ArrayList<String>(genDialectMap.keySet());
    }
}
