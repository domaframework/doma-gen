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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.seasar.doma.extension.gen.ClassConstants;
import org.seasar.doma.extension.gen.ColumnMeta;
import org.seasar.doma.extension.gen.GenNullPointerException;
import org.seasar.doma.extension.gen.Logger;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;

/**
 * Oracle Database 11g用の方言です。
 * 
 * @author taedium
 * 
 */
public class Oracle11GenDialect extends StandardGenDialect {

    /**
     * インスタンスを構築します。
     */
    public Oracle11GenDialect() {
        classNameMap.put("binary_double", Double.class.getName());
        classNameMap.put("binary_float", Float.class.getName());
        classNameMap.put("long", String.class.getName());
        classNameMap.put("long raw", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("nvarchar2", String.class.getName());
        classNameMap.put("raw", ClassConstants.bytes.getQualifiedName());
        classNameMap.put("varchar2", String.class.getName());
    }

    @Override
    public String getName() {
        return "oracle11";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.Oracle11Dialect.getQualifiedName();
    }

    @Override
    public boolean isJdbcCommentUnavailable() {
        return true;
    }

    @Override
    public String getTableComment(Connection connection, String catalogName,
            String schemaName, String tableName) throws SQLException {
        if (connection == null) {
            throw new GenNullPointerException("connection");
        }
        if (schemaName == null) {
            throw new GenNullPointerException("schemaName");
        }
        if (tableName == null) {
            throw new GenNullPointerException("tableName");
        }
        String sql = "select comments from all_tab_comments where owner = ? and table_name = ? and table_type in ('TABLE', 'VIEW')";
        Logger.info(
                String.format(sql.replace("?", "'%s'"), schemaName, tableName));
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1, schemaName);
            preparedStatement.setString(2, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
                return null;
            } finally {
                JdbcUtil.close(resultSet);
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    @Override
    public Map<String, String> getColumnCommentMap(Connection connection,
            String catalogName, String schemaName, String tableName)
            throws SQLException {
        if (connection == null) {
            throw new GenNullPointerException("connection");
        }
        if (schemaName == null) {
            throw new GenNullPointerException("schemaName");
        }
        if (tableName == null) {
            throw new GenNullPointerException("tableName");
        }

        String sql = "select column_name, comments from all_col_comments where owner = ? and table_name = ?";
        Logger.info(
                String.format(sql.replace("?", "'%s'"), schemaName, tableName));
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
            preparedStatement.setString(1, schemaName);
            preparedStatement.setString(2, tableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                Map<String, String> commentMap = new HashMap<String, String>();
                while (resultSet.next()) {
                    commentMap.put(resultSet.getString(1),
                            resultSet.getString(2));
                }
                return commentMap;
            } finally {
                JdbcUtil.close(resultSet);
            }
        } finally {
            JdbcUtil.close(preparedStatement);
        }
    }

    @Override
    public String getMappedPropertyClassName(ColumnMeta columnMeta) {
        if ("number".equals(columnMeta.getTypeName())) {
            if (columnMeta.getScale() != 0) {
                return BigDecimal.class.getName();
            }
            if (columnMeta.getLength() < 5) {
                return Short.class.getName();
            }
            if (columnMeta.getLength() < 10) {
                return Integer.class.getName();
            }
            if (columnMeta.getLength() < 19) {
                return Long.class.getName();
            }
            return BigInteger.class.getName();
        }
        return super.getMappedPropertyClassName(columnMeta);
    }

    @Override
    public boolean supportsSequence() {
        return true;
    }

    @Override
    public String convertToTimeLiteral(String value) {
        return "time" + super.convertToTimeLiteral(value);
    }

    @Override
    public String convertToDateLiteral(String value) {
        return "date" + super.convertToDateLiteral(value);
    }

    @Override
    public String convertToTimestampLiteral(String value) {
        return "timestamp" + super.convertToTimestampLiteral(value);
    }

}
