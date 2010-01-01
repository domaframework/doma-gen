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

import org.seasar.doma.extension.gen.internal.util.ClassUtil;
import org.seasar.doma.extension.gen.internal.util.StringUtil;

/**
 * エンティティ記述のファクトリです。
 * 
 * @author taedium
 */
public class EntityDescFactory {

    /** パッケージ名 */
    protected final String packageName;

    /** エンティティプロパティ記述のファクトリ */
    protected final EntityPropertyDescFactory entityPropertyDescFactory;

    /** 名前付けタイプ */
    protected final NamingType namingType;

    /** カタログ名を表示する場合 {@code true} */
    protected final boolean showCatalogName;

    /** スキーマ名を表示する場合 {@code true} */
    protected final boolean showSchemaName;

    /** テーブル名を表示する場合 {@code true} */
    protected final boolean showTableName;

    /** アクセッサーを使用する場合 {@code true} */
    protected final boolean useAccessor;

    /** リスナークラス名 */
    protected final String listenerClassName;

    /** スーパークラス名 */
    protected final String superclassName;

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     * 
     * @param packageName
     *            パッケージ名
     * @param superclassName
     *            スーパークラス名
     * @param listenerClassName
     *            リスナークラス名
     * @param entityPropertyDescFactory
     *            エンティティプロパティ記述のファクトリ
     * @param namingType
     *            ネーミング規約
     * @param showCatalogName
     *            カタログ名を表示する場合 {@code true}
     * @param showSchemaName
     *            スキーマ名を表示する場合 {@code true}
     * @param showTableName
     *            テーブル名を表示する場合 {@code true}
     * @param useAccessor
     *            アクセッサーを使用する場合 {@code true}
     */
    public EntityDescFactory(String packageName, String superclassName,
            String listenerClassName,
            EntityPropertyDescFactory entityPropertyDescFactory,
            NamingType namingType, boolean showCatalogName,
            boolean showSchemaName, boolean showTableName, boolean useAccessor) {
        if (packageName == null) {
            throw new GenNullPointerException("packageName");
        }
        if (entityPropertyDescFactory == null) {
            throw new GenNullPointerException("entityPropertyDescFactory");
        }
        this.packageName = packageName;
        this.superclassName = superclassName;
        this.listenerClassName = listenerClassName;
        this.entityPropertyDescFactory = entityPropertyDescFactory;
        this.namingType = namingType;
        this.showCatalogName = showCatalogName;
        this.showSchemaName = showSchemaName;
        this.showTableName = showTableName;
        this.useAccessor = useAccessor;
    }

    /**
     * エンティティ記述を作成します。
     * 
     * @param tableMeta
     *            テーブルメタデータ
     * @return エンティティ記述
     */
    public EntityDesc createEntityDesc(TableMeta tableMeta) {
        EntityDesc entityDesc = new EntityDesc();
        entityDesc.setNamingType(namingType);
        entityDesc.setCatalogName(tableMeta.getCatalogName());
        entityDesc.setSchemaName(tableMeta.getSchemaName());
        entityDesc.setTableName(tableMeta.getName());
        entityDesc.setPackageName(packageName);
        handleSimpleName(entityDesc, tableMeta);
        if (superclassName != null) {
            entityDesc.setSuperclassSimpleName(ClassUtil
                    .getSimpleName(superclassName));
        }
        if (listenerClassName != null) {
            entityDesc.setListenerClassSimpleName(ClassUtil
                    .getSimpleName(listenerClassName));
        }
        entityDesc.setCompositeId(tableMeta.hasCompositePrimaryKey());
        entityDesc.setComment(tableMeta.comment);
        entityDesc.setShowCatalogName(showCatalogName);
        entityDesc.setShowSchemaName(showSchemaName);
        entityDesc.setUseAccessor(useAccessor);
        handleShowTableName(entityDesc, tableMeta);
        handleEntityPropertyDesc(entityDesc, tableMeta);
        handleImportName(entityDesc, tableMeta);
        return entityDesc;
    }

    /**
     * 単純名を処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param tableMeta
     *            テーブルメタデータ
     */
    protected void handleSimpleName(EntityDesc entityDesc, TableMeta tableMeta) {
        String name = StringUtil.fromSnakeCaseToCamelCase(tableMeta.getName());
        entityDesc.setSimpleName(StringUtil.capitalize(name));
    }

    /**
     * カタログ名を表示するかどうか処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param tableMeta
     *            テーブルメタデータ
     */
    protected void handleShowTableName(EntityDesc entityDesc,
            TableMeta tableMeta) {
        if (showTableName || isNameDifferentBetweenEntityAndTable(entityDesc)) {
            entityDesc.setShowTableName(true);
        }
    }

    /**
     * エンティティ名とテーブル名が異なる場合 {@code true}を返します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @return エンティティ名とテーブル名が異なる場合 {@code true}
     */
    protected boolean isNameDifferentBetweenEntityAndTable(EntityDesc entityDesc) {
        return !entityDesc.getSimpleName().equalsIgnoreCase(entityDesc
                .getTableName());
    }

    /**
     * エンティティプロパティ記述を処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param tableMeta
     *            テーブルメタデータ
     */
    protected void handleEntityPropertyDesc(EntityDesc entityDesc,
            TableMeta tableMeta) {
        for (ColumnMeta columnMeta : tableMeta.getColumnMetaList()) {
            EntityPropertyDesc propertyDesc = entityPropertyDescFactory
                    .createEntityPropertyDesc(entityDesc, columnMeta);
            entityDesc.addEntityPropertyDesc(propertyDesc);
        }
    }

    /**
     * インポート名を処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param tableMeta
     *            テーブルメタデータ
     */
    protected void handleImportName(EntityDesc entityDesc, TableMeta tableMeta) {
        classDescSupport.addImportName(entityDesc, ClassConstant.Entity);
        if (entityDesc.getCatalogName() != null
                || entityDesc.getSchemaName() != null
                || entityDesc.getTableName() != null) {
            classDescSupport.addImportName(entityDesc, ClassConstant.Table);
        }
        if (superclassName != null) {
            classDescSupport.addImportName(entityDesc, superclassName);
        }
        if (listenerClassName != null) {
            classDescSupport.addImportName(entityDesc, listenerClassName);
        }
        if (namingType != null) {
            classDescSupport.addImportName(entityDesc, namingType
                    .getEnumConstant());
        }
        for (EntityPropertyDesc propertyDesc : entityDesc
                .getEntityPropertyDescs()) {
            if (propertyDesc.isId()) {
                classDescSupport.addImportName(entityDesc, ClassConstant.Id);
                if (propertyDesc.getGenerationType() != null) {
                    classDescSupport
                            .addImportName(entityDesc, ClassConstant.GeneratedValue);
                    classDescSupport
                            .addImportName(entityDesc, ClassConstant.GenerationType);
                    if (propertyDesc.getGenerationType() != null) {
                        classDescSupport.addImportName(entityDesc, propertyDesc
                                .getGenerationType().getEnumConstant());
                    }
                }
            }
            classDescSupport.addImportName(entityDesc, ClassConstant.Column);
            if (propertyDesc.isVersion()) {
                classDescSupport
                        .addImportName(entityDesc, ClassConstant.Version);
            }
            classDescSupport.addImportName(entityDesc, propertyDesc
                    .getPropertyClassName());
        }
    }

}
