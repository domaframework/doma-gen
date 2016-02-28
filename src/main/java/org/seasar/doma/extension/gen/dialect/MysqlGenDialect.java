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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.seasar.doma.extension.gen.ClassConstants;
import org.seasar.doma.extension.gen.ColumnMeta;

/**
 * MySQL用の方言です。
 * 
 * @author taedium
 * 
 */
public class MysqlGenDialect extends StandardGenDialect {

    /**
     * インスタンスを構築します。
     *
     * @see <a href="https://dev.mysql.com/doc/refman/5.6/ja/data-types.html">MySQL 5.6 リファレンスマニュアル / データ型</a>
     * @see <a href="https://dev.mysql.com/doc/connector-j/en/connector-j-reference-type-conversions.html">MySQL Connector/J Developer Guide / Java, JDBC and MySQL Types</a>
     */
    public MysqlGenDialect() {
        // 数値型
        classNameMap.put("bool", Boolean.class.getName());
        classNameMap.put("boolean", Boolean.class.getName());
        classNameMap.put("smallint", Short.class.getName());
        classNameMap.put("smallint unsigned", Integer.class.getName());
        classNameMap.put("mediumint", Integer.class.getName());
        classNameMap.put("mediumint unsigned", Integer.class.getName());
        classNameMap.put("int", Integer.class.getName());
        classNameMap.put("int unsigned", Long.class.getName());
        classNameMap.put("integer", Integer.class.getName());
        classNameMap.put("integer unsigned", Long.class.getName());
        classNameMap.put("bigint", Long.class.getName());
        classNameMap.put("bigint unsigned", BigInteger.class.getName());
        classNameMap.put("serial", BigInteger.class.getName());
        classNameMap.put("decimal", BigDecimal.class.getName());
        classNameMap.put("decimal unsigned", BigDecimal.class.getName());
        classNameMap.put("dec", BigDecimal.class.getName());
        classNameMap.put("dec unsigned", BigDecimal.class.getName());
        classNameMap.put("float", Float.class.getName());
        classNameMap.put("float unsigned", Float.class.getName());
        classNameMap.put("double", Double.class.getName());
        classNameMap.put("double unsigned", Double.class.getName());
        classNameMap.put("double precision", Double.class.getName());
        classNameMap.put("double precision unsigned", Double.class.getName());

        // 日付と時間型
        classNameMap.put("date", LocalDate.class.getName());
        classNameMap.put("datetime", LocalDateTime.class.getName());
        classNameMap.put("timestamp", LocalDateTime.class.getName());
        classNameMap.put("time", LocalTime.class.getName());
        classNameMap.put("year", Short.class.getName());

        // BLOB型
        classNameMap.put("tinyblob", Blob.class.getName());
        classNameMap.put("blob", Blob.class.getName());
        classNameMap.put("mediumblob", Blob.class.getName());
        classNameMap.put("longblob", Blob.class.getName());

        // BINARYおよびVARBINARY型
        classNameMap.put("binary", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("varbinary", ClassConstants.bytes.getQualifiedName());
    }

    @Override
    public String getName() {
        return "mysql";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.MysqlDialect.getQualifiedName();
    }

    @Override
    public String getMappedPropertyClassName(ColumnMeta columnMeta) {
        if ("bit".equals(columnMeta.getTypeName()) ||
                "tinyint".equals(columnMeta.getTypeName())) {
            return columnMeta.getLength() <= 1 ? Boolean.class.getName()
                    : Byte.class.getName();
        }
        if ("tinyint unsigned".equals(columnMeta.getTypeName())) {
            return columnMeta.getLength() <= 1 ? Boolean.class.getName()
                    : Short.class.getName();
        }
        return super.getMappedPropertyClassName(columnMeta);
    }

    @Override
    public boolean supportsIdentity() {
        return true;
    }

    @Override
    public boolean supportsSequence() {
        return true;
    }
}
