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

import java.sql.Types;
import java.time.LocalDate;

import junit.framework.TestCase;

/**
 * @author taedium
 * 
 */
public class StandardGenDialectTest extends TestCase {

    /**
     * 
     * @throws Exception
     */
    public void testReplacePropertyClassName() throws Exception {
        StandardGenDialect dialect = new StandardGenDialect();
        String localDateClassName = LocalDate.class.getName();
        String utilDateClassName = java.util.Date.class.getName();
        dialect.replacePropertyClassName(localDateClassName, utilDateClassName);
        assertEquals(utilDateClassName, dialect.classNameMap.get("date"));
        assertEquals(utilDateClassName,
                dialect.fallbackClassNameMap.get(Types.DATE));
    }

    /**
     * 
     * @throws Exception
     */
    public void testConvertToTimeLiteral() throws Exception {
        StandardGenDialect dialect = new StandardGenDialect();
        assertEquals("'a'", dialect.convertToTimeLiteral("a"));
    }

    /**
     * 
     * @throws Exception
     */
    public void testConvertToDateLiteral() throws Exception {
        StandardGenDialect dialect = new StandardGenDialect();
        assertEquals("'a'", dialect.convertToDateLiteral("a"));
    }

    /**
     * 
     * @throws Exception
     */
    public void testConvertToTimestampLiteral() throws Exception {
        StandardGenDialect dialect = new StandardGenDialect();
        assertEquals("'a'", dialect.convertToTimestampLiteral("a"));
    }
}
