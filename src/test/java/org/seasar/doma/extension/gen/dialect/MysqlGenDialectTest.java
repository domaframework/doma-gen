/*
 * Copyright 2004-2016 the Seasar Foundation and the Others.
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

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.seasar.doma.extension.gen.ColumnMeta;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author matsumana
 */
@RunWith(Theories.class)
public class MysqlGenDialectTest {

    @DataPoints
    public static Fixture[] params = {
            // 数値型
            new Fixture("bit", 1, "java.lang.Boolean"),
            new Fixture("bit", 2, "java.lang.Byte"),
            new Fixture("tinyint", 1, "java.lang.Boolean"),
            new Fixture("tinyint", 2, "java.lang.Byte"),
            new Fixture("bool", "java.lang.Boolean"),
            new Fixture("boolean", "java.lang.Boolean"),
            new Fixture("smallint", "java.lang.Short"),
            new Fixture("mediumint", "java.lang.Integer"),
            new Fixture("int", "java.lang.Integer"),
            new Fixture("integer", "java.lang.Integer"),
            new Fixture("bigint", "java.lang.Long"),
            new Fixture("serial", "java.math.BigInteger"),
            new Fixture("decimal", "java.math.BigDecimal"),
            new Fixture("dec", "java.math.BigDecimal"),
            new Fixture("float", "java.lang.Float"),
            new Fixture("double", "java.lang.Double"),
            new Fixture("double precision", "java.lang.Double"),
            new Fixture("tinyint unsigned", 1, "java.lang.Boolean"),
            new Fixture("tinyint unsigned", 2, "java.lang.Short"),
            new Fixture("smallint unsigned", "java.lang.Integer"),
            new Fixture("mediumint unsigned", "java.lang.Integer"),
            new Fixture("int unsigned", "java.lang.Long"),
            new Fixture("integer unsigned", "java.lang.Long"),
            new Fixture("bigint unsigned", "java.math.BigInteger"),
            new Fixture("decimal unsigned", "java.math.BigDecimal"),
            new Fixture("dec unsigned", "java.math.BigDecimal"),
            new Fixture("float unsigned", "java.lang.Float"),
            new Fixture("double unsigned", "java.lang.Double"),
            new Fixture("double precision unsigned", "java.lang.Double"),

            // 日付と時間型
            new Fixture("date", "java.time.LocalDate"),
            new Fixture("datetime", "java.time.LocalDateTime"),
            new Fixture("timestamp", "java.time.LocalDateTime"),
            new Fixture("time", "java.time.LocalTime"),
            new Fixture("year", "java.lang.Short"),

            // BLOB型とTEXT型
            new Fixture("tinyblob", "java.sql.Blob"),
            new Fixture("blob", "java.sql.Blob"),
            new Fixture("mediumblob", "java.sql.Blob"),
            new Fixture("longblob", "java.sql.Blob"),

            // BINARYおよびVARBINARY型
            new Fixture("binary", ".byte[]"),
            new Fixture("varbinary", ".byte[]"),
    };

    @Theory
    public void theory(Fixture param) {
        MysqlGenDialect sut = new MysqlGenDialect();
        ColumnMeta columnMeta = new ColumnMeta();
        columnMeta.setTypeName(param.value);
        columnMeta.setLength(param.length);
        String actual = sut.getMappedPropertyClassName(columnMeta);

        assertThat(actual, is(param.expected));
    }

    static class Fixture {
        String value;
        int length;
        String expected;

        Fixture(String value, String expected) {
            this.value = value;
            this.expected = expected;
        }

        Fixture(String value, int length, String expected) {
            this.value = value;
            this.length = length;
            this.expected = expected;
        }
    }
}
