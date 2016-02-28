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
package org.seasar.doma.extension.gen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;

/**
 * @author nakamura-to
 *
 */
public class ResultSetMetaReader {

    /** 方言 */
    protected final GenDialect dialect;

    /** データソース */
    protected final DataSource dataSource;

    public ResultSetMetaReader(GenDialect dialect, DataSource dataSource) {
        if (dialect == null) {
            throw new GenNullPointerException("dialect");
        }
        if (dataSource == null) {
            throw new GenNullPointerException("dataSource");
        }
        this.dialect = dialect;
        this.dataSource = dataSource;
    }

    public TableMeta read(String sql) {
        if (sql == null) {
            throw new GenNullPointerException("sql");
        }
        Connection connection = JdbcUtil.getConnection(dataSource);
        try {
            PreparedStatement preparedStatement = JdbcUtil.prepareStatement(
                    connection, sql);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    return createTableMeta(resultSet.getMetaData());
                } finally {
                    JdbcUtil.close(resultSet);
                }
            } finally {
                JdbcUtil.close(preparedStatement);
            }
        } catch (SQLException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    protected TableMeta createTableMeta(ResultSetMetaData rsmd)
            throws SQLException {
        TableMeta tableMeta = new TableMeta();
        for (int i = 1, max = rsmd.getColumnCount() + 1; i < max; i++) {
            ColumnMeta columnMeta = new ColumnMeta();
            columnMeta.setName(rsmd.getColumnLabel(i));
            columnMeta.setSqlType(rsmd.getColumnType(i));
            columnMeta.setTypeName(rsmd.getTableName(i).toLowerCase());
            columnMeta.setLength(rsmd.getPrecision(i));
            columnMeta.setScale(rsmd.getScale(i));
            tableMeta.addColumnMeta(columnMeta);
        }
        return tableMeta;
    }
}
