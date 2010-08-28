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
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.seasar.doma.extension.gen.ClassConstants;
import org.seasar.doma.extension.gen.ColumnMeta;
import org.seasar.doma.extension.gen.GenNullPointerException;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;
import org.seasar.doma.extension.gen.internal.util.TableUtil;

/**
 * 標準の方言です。
 * 
 * @author taedium
 * 
 */
public class StandardGenDialect implements GenDialect {

    /** RDBMSのカラムの型をキー、Javaのクラス名を値とするマップ */
    protected final Map<String, String> classNameMap = new HashMap<String, String>();

    /** JDBCのSQL型をキー、Javaのクラス名を値とするマップ */
    protected final Map<Integer, String> fallbackClassNameMap = new HashMap<Integer, String>();

    /**
     * インスタンスを構築します。
     */
    public StandardGenDialect() {
        classNameMap.put("bigint", Long.class.getName());
        classNameMap.put("binary", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("bit", Boolean.class.getName());
        classNameMap.put("blob", Blob.class.getName());
        classNameMap.put("boolean", Boolean.class.getName());
        classNameMap.put("char", String.class.getName());
        classNameMap.put("clob", Clob.class.getName());
        classNameMap.put("date", Date.class.getName());
        classNameMap.put("decimal", BigDecimal.class.getName());
        classNameMap.put("double", Double.class.getName());
        classNameMap.put("float", Float.class.getName());
        classNameMap.put("integer", Integer.class.getName());
        classNameMap.put("longnvarchar", String.class.getName());
        classNameMap.put("longvarbinary", ClassConstants.bytes
                .getQualifiedName());
        classNameMap.put("longvarchar", String.class.getName());
        classNameMap.put("nclob", NClob.class.getName());
        classNameMap.put("nchar", String.class.getName());
        classNameMap.put("numeric", BigDecimal.class.getName());
        classNameMap.put("nvarchar", String.class.getName());
        classNameMap.put("real", Float.class.getName());
        classNameMap.put("smallint", Short.class.getName());
        classNameMap.put("time", Time.class.getName());
        classNameMap.put("timestamp", Timestamp.class.getName());
        classNameMap.put("tinyint", Short.class.getName());
        classNameMap.put("varbinary", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("varchar", String.class.getName());

        fallbackClassNameMap.put(Types.BIGINT, Long.class.getName());
        fallbackClassNameMap.put(Types.BINARY, byte[].class.getName());
        fallbackClassNameMap.put(Types.BIT, Boolean.class.getName());
        fallbackClassNameMap.put(Types.BLOB, Blob.class.getName());
        fallbackClassNameMap.put(Types.BOOLEAN, Boolean.class.getName());
        fallbackClassNameMap.put(Types.CHAR, String.class.getName());
        fallbackClassNameMap.put(Types.CLOB, Clob.class.getName());
        fallbackClassNameMap.put(Types.DATE, Date.class.getName());
        fallbackClassNameMap.put(Types.DECIMAL, BigDecimal.class.getName());
        fallbackClassNameMap.put(Types.DOUBLE, Double.class.getName());
        fallbackClassNameMap.put(Types.FLOAT, Float.class.getName());
        fallbackClassNameMap.put(Types.INTEGER, Integer.class.getName());
        fallbackClassNameMap.put(Types.LONGNVARCHAR, String.class.getName());
        fallbackClassNameMap.put(Types.LONGVARBINARY, ClassConstants.bytes
                .getQualifiedName());
        fallbackClassNameMap.put(Types.LONGVARCHAR, String.class.getName());
        fallbackClassNameMap.put(Types.NCHAR, String.class.getName());
        fallbackClassNameMap.put(Types.NCLOB, NClob.class.getName());
        fallbackClassNameMap.put(Types.NUMERIC, BigDecimal.class.getName());
        fallbackClassNameMap.put(Types.REAL, Float.class.getName());
        fallbackClassNameMap.put(Types.SMALLINT, Short.class.getName());
        fallbackClassNameMap.put(Types.TIME, Time.class.getName());
        fallbackClassNameMap.put(Types.TIMESTAMP, Timestamp.class.getName());
        fallbackClassNameMap.put(Types.TINYINT, Short.class.getName());
        fallbackClassNameMap.put(Types.VARBINARY, ClassConstants.bytes
                .getQualifiedName());
        fallbackClassNameMap.put(Types.VARCHAR, String.class.getName());
        fallbackClassNameMap.put(Types.NVARCHAR, String.class.getName());
    }

    @Override
    public String getName() {
        return "standard";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.StandardDialect.getQualifiedName();
    }

    @Override
    public boolean isJdbcCommentUnavailable() {
        return false;
    }

    @Override
    public String getDefaultSchemaName(String userName) {
        return userName;
    }

    @Override
    public boolean isAutoIncrement(Connection connection, String catalogName,
            String schemaName, String tableName, String columnName)
            throws SQLException {
        if (connection == null) {
            throw new GenNullPointerException("connection");
        }
        if (tableName == null) {
            throw new GenNullPointerException("tableName");
        }
        if (columnName == null) {
            throw new GenNullPointerException("columnName");
        }
        String fullTableName = TableUtil
                .getQualifiedTableName(catalogName, schemaName, tableName);
        String sql = "select " + columnName + " from " + fullTableName
                + " where 1 = 0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                ResultSetMetaData rsMetaData = resultSet.getMetaData();
                return rsMetaData.isAutoIncrement(1);
            } finally {
                JdbcUtil.close(resultSet);
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    @Override
    public String getTableComment(Connection connection, String catalogName,
            String schemaName, String tableName) throws SQLException {
        return "";
    }

    @Override
    public Map<String, String> getColumnCommentMap(Connection connection,
            String catalogName, String schemaName, String tableName)
            throws SQLException {
        return Collections.emptyMap();
    }

    @Override
    public boolean supportsIdentity() {
        return false;
    }

    @Override
    public boolean supportsSequence() {
        return false;
    }

    @Override
    public String getMappedPropertyClassName(ColumnMeta columnMeta) {
        String mappedClassName = classNameMap.get(columnMeta.getTypeName());
        if (mappedClassName != null) {
            return mappedClassName;
        }
        mappedClassName = fallbackClassNameMap.get(columnMeta.getSqlType());
        if (mappedClassName != null) {
            return mappedClassName;
        }
        return null;
    }

    @Override
    public void replacePropertyClassName(String oldClassName,
            String newClassName) {
        if (oldClassName == null) {
            throw new GenNullPointerException("oldClassName");
        }
        if (newClassName == null) {
            throw new GenNullPointerException("newClassName");
        }
        for (Map.Entry<String, String> entry : classNameMap.entrySet()) {
            if (oldClassName.equals(entry.getValue())) {
                entry.setValue(newClassName);
            }
        }
        for (Map.Entry<Integer, String> entry : fallbackClassNameMap.entrySet()) {
            if (oldClassName.equals(entry.getValue())) {
                entry.setValue(newClassName);
            }
        }
    }

}
