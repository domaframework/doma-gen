/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
import java.util.List;

/**
 * エンティティ記述です。
 * 
 * @author taedium
 */
public class EntityDesc extends ClassDesc {

    /** カタログ名 */
    protected String catalogName;

    /** スキーマ名 */
    protected String schemaName;

    /** テーブル名 */
    protected String tableName;

    /** スーパークラスの単純名 */
    protected String superclassSimpleName;

    /** リスナークラスの単純名 */
    protected String listenerClassSimpleName;

    /** ネーミング規約 */
    protected NamingType namingType;

    /** 複合識別子を持つ場合 {@code true} */
    protected boolean compositeId;

    /** カタログ名を表示する場合 {@code true} */
    protected boolean showCatalogName;

    /** スキーマ名を表示する場合 {@code true} */
    protected boolean showSchemaName;

    /** テーブル名を表示する場合 {@code true} */
    protected boolean showTableName;

    /** アクセッサーを使用する場合 {@code true} */
    protected boolean useAccessor;

    /** プロパティのモデルのリスト */
    protected final List<EntityPropertyDesc> entityPropertyDescs = new ArrayList<EntityPropertyDesc>();

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
     * カタログ名を返します。
     * 
     * @return カタログ名
     */
    public String getCatalogName() {
        return catalogName;
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
     * スキーマ名を返します。
     * 
     * @return スキーマ名
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * テーブル名 を設定します。
     * 
     * @param tableName
     *            テーブル名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * テーブル名 を返します。
     * 
     * @return テーブル名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * スーパークラスの単純名を返します。
     * 
     * @return スーパークラスの単純名
     */
    public String getSuperclassSimpleName() {
        return superclassSimpleName;
    }

    /**
     * スーパークラスの単純名を設定します。
     * 
     * @param superclassSimpleName
     *            スーパークラスの単純名
     */
    public void setSuperclassSimpleName(String superclassSimpleName) {
        this.superclassSimpleName = superclassSimpleName;
    }

    /**
     * リスナークラスの単純名を返します。
     * 
     * @return リスナークラスの単純名
     */
    public String getListenerClassSimpleName() {
        return listenerClassSimpleName;
    }

    /**
     * リスナークラスの単純名を設定します。
     * 
     * @param listenerClassSimpleName
     *            リスナークラスの単純名
     */
    public void setListenerClassSimpleName(String listenerClassSimpleName) {
        this.listenerClassSimpleName = listenerClassSimpleName;
    }

    /**
     * ネーミング規約を返します。
     * 
     * @return ネーミング規約
     */
    public NamingType getNamingType() {
        return namingType;
    }

    /**
     * ネーミング規約を設定します。
     * 
     * @param namingType
     *            ネーミング規約
     */
    public void setNamingType(NamingType namingType) {
        this.namingType = namingType;
    }

    /**
     * 複合識別子を持つ場合 {@code true} を設定します。
     * 
     * @param compositeId
     *            複合識別子を持つ場合 {@code true}
     */
    public void setCompositeId(boolean compositeId) {
        this.compositeId = compositeId;
    }

    /**
     * 複合識別子を持つ場合 {@code true} を返します。
     * 
     * @return 複合識別子を持つ場合 {@code true}
     */
    public boolean isCompositeId() {
        return compositeId;
    }

    /**
     * カタログ名を表示する場合 {@code true} を返します。
     * 
     * @return カタログ名を表示する場合 {@code true}
     */
    public boolean isShowCatalogName() {
        return showCatalogName;
    }

    /**
     * カタログ名を表示する場合 {@code true} を設定します。
     * 
     * @param showCatalogName
     *            カタログ名を表示する場合 {@code true}
     */
    public void setShowCatalogName(boolean showCatalogName) {
        this.showCatalogName = showCatalogName;
    }

    /**
     * スキーマ名を表示する場合 {@code true} を返します。
     * 
     * @return スキーマ名を表示する場合 {@code true}
     */
    public boolean isShowSchemaName() {
        return showSchemaName;
    }

    /**
     * スキーマ名を表示する場合 {@code true} を設定します。
     * 
     * @param showSchemaName
     *            スキーマ名を表示する場合 {@code true}
     */
    public void setShowSchemaName(boolean showSchemaName) {
        this.showSchemaName = showSchemaName;
    }

    /**
     * テーブル名を表示する場合 {@code true} を返します。
     * 
     * @return テーブル名を表示する場合 {@code true}
     */
    public boolean isShowTableName() {
        return showTableName;
    }

    /**
     * テーブル名を表示する場合 {@code true} を設定します。
     * 
     * @param showSchemaName
     *            テーブル名を表示する場合 {@code true}
     */
    public void setShowTableName(boolean showTableName) {
        this.showTableName = showTableName;
    }

    /**
     * アクセッサーを使用する場合 {@code true} を返します。
     * 
     * @return アクセッサーを使用する場合 {@code true}
     */
    public boolean isUseAccessor() {
        return useAccessor;
    }

    /**
     * アクセッサーを使用する場合 {@code true} を設定します。
     * 
     * @param useAccessor
     *            アクセッサーを使用する場合 {@code true}
     */
    public void setUseAccessor(boolean useAccessor) {
        this.useAccessor = useAccessor;
    }

    /**
     * エンティティプロパティ記述を追加します。
     * 
     * @param entityPropertyDesc
     *            エンティティプロパティ記述
     */
    public void addEntityPropertyDesc(EntityPropertyDesc entityPropertyDesc) {
        entityPropertyDescs.add(entityPropertyDesc);
    }

    /**
     * エンティティプロパティ記述のリストを返します。
     * 
     * @return エンティティプロパティ記述のリスト
     */
    public List<EntityPropertyDesc> getEntityPropertyDescs() {
        return entityPropertyDescs;
    }

}