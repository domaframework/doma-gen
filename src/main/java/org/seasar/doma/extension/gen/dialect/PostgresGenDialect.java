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

import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.seasar.doma.extension.gen.ClassConstants;

/**
 * PostgreSQL用の方言です。
 * 
 * @author taedium
 * 
 */
public class PostgresGenDialect extends StandardGenDialect {

    /**
     * インスタンスを構築します。
     */
    public PostgresGenDialect() {
        classNameMap.put("bigserial", Long.class.getName());
        classNameMap.put("bit", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("bool", Boolean.class.getName());
        classNameMap.put("bpchar", String.class.getName());
        classNameMap.put("bytea", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("float4", Float.class.getName());
        classNameMap.put("float8", Double.class.getName());
        classNameMap.put("int2", Short.class.getName());
        classNameMap.put("int4", Integer.class.getName());
        classNameMap.put("int8", Long.class.getName());
        classNameMap.put("money", Float.class.getName());
        classNameMap.put("oid", Blob.class.getName());
        classNameMap.put("serial", Integer.class.getName());
        classNameMap.put("text", String.class.getName());
        classNameMap.put("timestamptz", LocalDateTime.class.getName());
        classNameMap.put("timetz", LocalTime.class.getName());
        classNameMap.put("varbit", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("varchar", String.class.getName());
    }

    @Override
    public String getName() {
        return "postgres";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.PostgresDialect.getQualifiedName();
    }

    @Override
    public String getDefaultSchemaName(String userName) {
        return null;
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
