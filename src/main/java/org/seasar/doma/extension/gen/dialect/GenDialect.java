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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.seasar.doma.extension.gen.ColumnMeta;

/**
 * 方言です。
 * 
 * @author taedium
 * 
 */
public interface GenDialect {

    /**
     * 名前を返します。
     * 
     * @return 名前
     */
    String getName();

    /**
     * 方言クラス名を返します。
     * 
     * @return 方言クラス名
     */
    String getDialectClassName();

    /**
     * デフォルトのスキーマ名を返します。
     * 
     * @param userName
     *            ユーザ名
     * @return デフォルトのスキーマ名
     */
    String getDefaultSchemaName(String userName);

    /**
     * カラムにマップされたプロパティのクラス名を返します。
     * 
     * @param columnMeta
     *            カラムメタデータ
     * @return カラムにマップされたクラス名
     */
    String getMappedPropertyClassName(ColumnMeta columnMeta);

    /**
     * プロパティのクラス名を置き換えます。
     * 
     * @param oldClassName
     *            古いクラス名
     * @param newClassName
     *            新しいクラス名
     * @since 1.9.0
     */
    void replacePropertyClassName(String oldClassName, String newClassName);

    /**
     * アイデンティティをサポートする場合 {@code true} を返します。
     * 
     * @return アイデンティティをサポートする場合 {@code true}
     */
    boolean supportsIdentity();

    /**
     * シーケンスをサポートする場合 {@code true} を返します。
     * 
     * @return シーケンスをサポートする場合 {@code true}
     */
    boolean supportsSequence();

    /**
     * カラムが自動インクリメントに設定されている場合 {@code true} を返します。
     * 
     * @param connection
     *            コネクション
     * @param catalogName
     *            カタログ名
     * @param schemaName
     *            スキーマ名
     * @param tableName
     *            テーブル名
     * @param columnName
     *            カラム名
     * @return カラムが自動インクリメントに設定されている場合 {@code true}
     * @throws SQLException
     *             SQLに関する例外が発生した場合
     */
    boolean isAutoIncrement(Connection connection, String catalogName,
            String schemaName, String tableName, String columnName)
            throws SQLException;

    /**
     * JDBCによりコメントの取得が不可能な場合 {@code true} を返します。
     * 
     * @return JDBCによりコメントの取得が不可能な場合 {@code true}
     */
    boolean isJdbcCommentUnavailable();

    /**
     * テーブルのコメントを取得します。
     * 
     * @param connection
     *            コネクション
     * @param catalogName
     *            カタログ名
     * @param schemaName
     *            スキーマ名
     * @param tableName
     *            テーブル名
     * @return テーブルのコメント
     * @throws SQLException
     *             SQLに関する例外が発生した場合
     */
    String getTableComment(Connection connection, String catalogName,
            String schemaName, String tableName) throws SQLException;

    /**
     * カラムのコメントのマップを取得します。
     * 
     * @param connection
     *            コネクション
     * @param catalogName
     *            カタログ名
     * @param schemaName
     *            スキーマ名
     * @param tableName
     *            テーブル名
     * @return カラム名をキー、コメントを値とするマップ
     * @throws SQLException
     *             SQLに関する例外が発生した場合
     */
    Map<String, String> getColumnCommentMap(Connection connection,
            String catalogName, String schemaName, String tableName)
            throws SQLException;

    /**
     * 時刻を表すリテラルを返します。
     * 
     * @param value
     *            値
     * @return リテラル
     * @since 1.11.0
     */
    String convertToTimeLiteral(String value);

    /**
     * 日付を表すリテラルを返します。
     * 
     * @param value
     *            値
     * @return リテラル
     * @since 1.11.0
     */
    String convertToDateLiteral(String value);

    /**
     * タイムスタンプを表すリテラルを返します。
     * 
     * @param value
     *            値
     * @return リテラル
     * @since 1.11.0
     */
    String convertToTimestampLiteral(String value);

}
