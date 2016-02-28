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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;

/**
 * テーブルメタデータのリーダです。
 * 
 * @author taedium
 */
public class TableMetaReader {

    /** 方言 */
    protected final GenDialect dialect;

    /** データソース */
    protected final DataSource dataSource;

    /** スキーマ名 */
    protected final String schemaName;

    /** 読み取り対象とするテーブル名のパターン */
    protected final Pattern tableNamePattern;

    /** 読み取り非対象とするテーブル名のパターン */
    protected final Pattern ignoredTableNamePattern;

    protected final List<String> tableTypes;

    /**
     * インスタンスを構築します。
     * 
     * @param dialect
     *            方言
     * @param dataSource
     *            データソース
     * @param schemaName
     *            スキーマ名
     * @param tableNamePattern
     *            読み取り対象とするテーブル名のパターン
     * @param ignoredTableNamePattern
     *            読み取り非対象とするテーブル名のパターン
     * @param tableTypes
     *            テーブルの型のリスト
     */
    public TableMetaReader(GenDialect dialect, DataSource dataSource,
            String schemaName, String tableNamePattern,
            String ignoredTableNamePattern, List<String> tableTypes) {
        if (dialect == null) {
            throw new GenNullPointerException("dialect");
        }
        if (dataSource == null) {
            throw new GenNullPointerException("dataSource");
        }
        if (tableNamePattern == null) {
            throw new GenNullPointerException("tableNamePattern");
        }
        if (ignoredTableNamePattern == null) {
            throw new GenNullPointerException("ignoreTableNamePattern");
        }
        if (tableTypes == null) {
            throw new GenNullPointerException("tableTypes");
        }
        this.dialect = dialect;
        this.dataSource = dataSource;
        this.schemaName = schemaName;
        this.tableNamePattern = Pattern
                .compile(tableNamePattern, Pattern.CASE_INSENSITIVE);
        this.ignoredTableNamePattern = Pattern
                .compile(ignoredTableNamePattern, Pattern.CASE_INSENSITIVE);
        this.tableTypes = tableTypes;
    }

    /**
     * テーブルメタデータを読み取ります。
     * 
     * @return テーブルメタデータ
     */
    public List<TableMeta> read() {
        Connection con = JdbcUtil.getConnection(dataSource);
        try {
            DatabaseMetaData metaData = con.getMetaData();
            List<TableMeta> tableMetas = getTableMetas(metaData, schemaName != null ? schemaName
                    : getDefaultSchemaName(metaData));
            for (TableMeta tableMeta : tableMetas) {
                Set<String> primaryKeySet = getPrimaryKeys(metaData, tableMeta);
                handleColumnMeta(metaData, tableMeta, primaryKeySet);
            }
            if (dialect.isJdbcCommentUnavailable()) {
                readCommentFromDictinary(con, tableMetas);
            }
            return tableMetas;
        } catch (SQLException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        } finally {
            JdbcUtil.close(con);
        }
    }

    protected void handleColumnMeta(DatabaseMetaData metaData,
            TableMeta tableMeta, Set<String> primaryKeySet) throws SQLException {
        for (ColumnMeta columnMeta : getDbColumnMetas(metaData, tableMeta)) {
            if (primaryKeySet.contains(columnMeta.getName())) {
                columnMeta.setPrimaryKey(true);
                if (primaryKeySet.size() == 1) {
                    columnMeta
                            .setAutoIncrement(isAutoIncrement(metaData, tableMeta, columnMeta
                                    .getName()));
                }
            }
            tableMeta.addColumnMeta(columnMeta);
        }
    }

    protected String getDefaultSchemaName(DatabaseMetaData metaData)
            throws SQLException {
        String userName = metaData.getUserName();
        return dialect.getDefaultSchemaName(userName);
    }

    protected List<TableMeta> getTableMetas(DatabaseMetaData metaData,
            String schemaName) throws SQLException {
        List<TableMeta> results = new ArrayList<TableMeta>();
        ResultSet rs = metaData
                .getTables(null, schemaName, null, this.tableTypes
                        .toArray(new String[this.tableTypes.size()]));
        try {
            while (rs.next()) {
                TableMeta tableMeta = new TableMeta();
                tableMeta.setCatalogName(rs.getString("TABLE_CAT"));
                tableMeta.setSchemaName(rs.getString("TABLE_SCHEM"));
                tableMeta.setName(rs.getString("TABLE_NAME"));
                tableMeta.setComment(rs.getString("REMARKS"));
                if (isTargetTable(tableMeta)) {
                    results.add(tableMeta);
                }
            }
            return results;
        } finally {
            JdbcUtil.close(rs);
        }
    }

    protected boolean isTargetTable(TableMeta dbTableMeta) {
        String name = dbTableMeta.getName();
        if (!tableNamePattern.matcher(name).matches()) {
            return false;
        }
        if (ignoredTableNamePattern.matcher(name).matches()) {
            return false;
        }
        return true;
    }

    protected List<ColumnMeta> getDbColumnMetas(DatabaseMetaData metaData,
            TableMeta tableMeta) throws SQLException {
        List<ColumnMeta> results = new ArrayList<ColumnMeta>();
        ResultSet rs = metaData
                .getColumns(tableMeta.getCatalogName(), tableMeta
                        .getSchemaName(), tableMeta.getName(), null);
        try {
            while (rs.next()) {
                ColumnMeta columnMeta = new ColumnMeta();
                columnMeta.setName(rs.getString("COLUMN_NAME"));
                columnMeta.setSqlType(rs.getInt("DATA_TYPE"));
                columnMeta.setTypeName(rs.getString("TYPE_NAME").toLowerCase());
                columnMeta.setLength(rs.getInt("COLUMN_SIZE"));
                columnMeta.setScale(rs.getInt("DECIMAL_DIGITS"));
                columnMeta.setNullable(rs.getBoolean("NULLABLE"));
                columnMeta.setDefaultValue(rs.getString("COLUMN_DEF"));
                columnMeta.setComment(rs.getString("REMARKS"));
                results.add(columnMeta);
            }
            return results;
        } finally {
            JdbcUtil.close(rs);
        }
    }

    protected Set<String> getPrimaryKeys(DatabaseMetaData metaData,
            TableMeta tableMeta) throws SQLException {
        Set<String> results = new HashSet<String>();
        ResultSet rs = metaData
                .getPrimaryKeys(tableMeta.getCatalogName(), tableMeta
                        .getSchemaName(), tableMeta.getName());
        try {
            while (rs.next()) {
                results.add(rs.getString("COLUMN_NAME"));
            }
        } finally {
            JdbcUtil.close(rs);
        }
        return results;
    }

    protected boolean isAutoIncrement(DatabaseMetaData metaData,
            TableMeta tableMeta, String columnName) throws SQLException {
        return dialect.isAutoIncrement(metaData.getConnection(), tableMeta
                .getCatalogName(), tableMeta.getSchemaName(), tableMeta
                .getName(), columnName);
    }

    protected void readCommentFromDictinary(Connection connection,
            List<TableMeta> dbTableMetaList) throws SQLException {
        for (TableMeta tableMeta : dbTableMetaList) {
            String tableComment = dialect.getTableComment(connection, tableMeta
                    .getCatalogName(), tableMeta.getSchemaName(), tableMeta
                    .getName());
            tableMeta.setComment(tableComment);
            Map<String, String> columnCommentMap = dialect
                    .getColumnCommentMap(connection, tableMeta.getCatalogName(), tableMeta
                            .getSchemaName(), tableMeta.getName());
            for (ColumnMeta columnMeta : tableMeta.getColumnMetas()) {
                String columnName = columnMeta.getName();
                if (columnCommentMap.containsKey(columnName)) {
                    String columnComment = columnCommentMap.get(columnName);
                    columnMeta.setComment(columnComment);
                }
            }
        }
    }
}
