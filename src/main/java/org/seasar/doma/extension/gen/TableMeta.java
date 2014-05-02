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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.doma.extension.gen.internal.util.TableUtil;

/**
 * データベースのテーブルメタデータです。
 * 
 * @author taedium
 */
public class TableMeta {

    /** カタログ名 */
    protected String catalogName;

    /** スキーマ名 */
    protected String schemaName;

    /** 名前 */
    protected String name;

    /** コメント */
    protected String comment;

    /** カラムメタデータのリスト */
    protected final List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();

    /** 主キーのカラムメタデータのリスト */
    protected final List<ColumnMeta> primaryKeyColumnMetas = new ArrayList<ColumnMeta>();

    /**
     * カタログ名を返します。
     * 
     * @return カタログ名
     */
    public String getCatalogName() {
        return catalogName;
    }

    /**
     * カタログ名を設定します。
     * 
     * @param catalogName
     *            カタログ名
     */
    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * スキーマ名を返します
     * 
     * @return スキーマ名
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * スキーマ名を設定します。
     * 
     * @param schemaName
     *            スキーマ名
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * 名前を返します。
     * 
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * 名前を設定します。
     * 
     * @param name
     *            名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * コメントを返します。
     * 
     * @return コメント
     */
    public String getComment() {
        return comment;
    }

    /**
     * コメントを設定します。
     * 
     * @param comment
     *            コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * カラムのメタデータのリストを返します。
     * 
     * @return カラムのメタデータのリスト
     */
    public List<ColumnMeta> getColumnMetas() {
        return Collections.unmodifiableList(columnMetas);
    }

    /**
     * カラムのメタデータを追加します。
     * 
     * @param columnMeta
     *            カラム記述
     */
    public void addColumnMeta(ColumnMeta columnMeta) {
        columnMetas.add(columnMeta);
        columnMeta.setTableMeta(this);
        if (columnMeta.isPrimaryKey()) {
            primaryKeyColumnMetas.add(columnMeta);
        }
    }

    /**
     * 主キーのカラムメタデータのリストを返します。
     * 
     * @return 主キーのカラムメタデータのリスト
     */
    public List<ColumnMeta> getPrimaryKeyColumnMetas() {
        return Collections.unmodifiableList(primaryKeyColumnMetas);
    }

    /**
     * 完全なテーブル名を返します。
     * 
     * @return 完全なテーブル名
     */
    public String getQualifiedTableName() {
        return TableUtil.getQualifiedTableName(catalogName, schemaName, name);
    }

    /**
     * 複合主キーを持つ場合{@code true}を返します。
     * 
     * @return 複合主キーを持つ場合{@code true}、そうでない場合{@code false}
     */
    public boolean hasCompositePrimaryKey() {
        return primaryKeyColumnMetas.size() > 1;
    }
}
