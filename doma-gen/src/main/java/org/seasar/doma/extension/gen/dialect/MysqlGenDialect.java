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

import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

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
     */
    public MysqlGenDialect() {
        classNameMap.put("bigint unsigned", BigInteger.class.getName());
        classNameMap.put("datetime", Timestamp.class.getName());
        classNameMap.put("int", Integer.class.getName());
        classNameMap.put("int unsigned", Long.class.getName());
        classNameMap.put("longblob", Blob.class.getName());
        classNameMap.put("longtext", Clob.class.getName());
        classNameMap.put("mediumblob", Blob.class.getName());
        classNameMap.put("mediumint", Integer.class.getName());
        classNameMap.put("mediumint unsigned", Integer.class.getName());
        classNameMap.put("mediumtext", Clob.class.getName());
        classNameMap.put("smallint unsigned", Integer.class.getName());
        classNameMap.put("tinyblob", Blob.class.getName());
        classNameMap.put("tinyint", Byte.class.getName());
        classNameMap.put("tinyint unsigned", Short.class.getName());
        classNameMap.put("tinytext", Clob.class.getName());
        classNameMap.put("text", Clob.class.getName());
        classNameMap.put("year", Date.class.getName());
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
    public String getMappedClassName(ColumnMeta columnMeta) {
        if ("bit".equalsIgnoreCase(columnMeta.getTypeName())) {
            return columnMeta.getLength() == 1 ? Boolean.class.getName()
                    : ClassConstants.bytes.getQualifiedName();
        }
        return super.getMappedClassName(columnMeta);
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
