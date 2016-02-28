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
package org.seasar.doma.extension.gen.internal.util;

import junit.framework.TestCase;

/**
 * @author nakamura-to
 *
 */
public class JdbcUtilTest extends TestCase {

    public void testInferDialectName() throws Exception {
        String dialectName = JdbcUtil
                .inferDialectName("jdbc:postgresql://localhost/hoge");
        assertEquals("postgres", dialectName);
    }

    public void testInferDialectName_mysql() throws Exception {
        String dialectName = JdbcUtil
                .inferDialectName("jdbc:mysql://localhost:3306/hoge");
        assertEquals("mysql", dialectName);
    }

    public void testInferDialectName_unknown() throws Exception {
        String dialectName = JdbcUtil
                .inferDialectName("jdbc:unknown://localhost/hoge");
        assertNull(dialectName);
    }

    public void testInferDialectName_invalidUrl() throws Exception {
        String dialectName = JdbcUtil.inferDialectName("localhost/hoge");
        assertNull(dialectName);
    }

    public void testInferDriverClassName() throws Exception {
        String driverClassName = JdbcUtil
                .inferDriverClassName("jdbc:postgresql://localhost/hoge");
        assertEquals("org.postgresql.Driver", driverClassName);
    }

    public void testInferDriverClassName_mysql() throws Exception {
        String driverClassName = JdbcUtil
                .inferDriverClassName("jdbc:mysql://localhost:3306/hoge");
        assertEquals("com.mysql.jdbc.Driver", driverClassName);
    }

    public void testInferDriverClassName_unknown() throws Exception {
        String driverClassName = JdbcUtil
                .inferDriverClassName("jdbc:unknown://localhost/hoge");
        assertNull(driverClassName);
    }

    public void testInferDriverClassName_invalidUrl() throws Exception {
        String driverClassName = JdbcUtil
                .inferDriverClassName("localhost/hoge");
        assertNull(driverClassName);
    }

}
