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

    /** ネーミング規約 */
    protected final NamingType namingType;

    /** 元のステートを表すプロパティの名前 */
    protected String originalStatesPropertyName;

    /** カタログ名を表示する場合 {@code true} */
    protected final boolean showCatalogName;

    /** スキーマ名を表示する場合 {@code true} */
    protected final boolean showSchemaName;

    /** テーブル名を表示する場合 {@code true} */
    protected final boolean showTableName;

    /** データベースのコメントを表示する場合 {@code true} */
    protected final boolean showDbComment;

    /** アクセッサーを使用する場合 {@code true} */
    protected final boolean useAccessor;

    /** エンティティリスナーを使用する場合 {@code true} */
    protected final boolean useListener;

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
     * @param useListener
     *            エンティティリスナーを使用する場合 {@code true}
     */
    public EntityDescFactory(String packageName, String superclassName,
            EntityPropertyDescFactory entityPropertyDescFactory,
            NamingType namingType, String originalStatesPropertyName,
            boolean showCatalogName, boolean showSchemaName,
            boolean showTableName, boolean showDbComment, boolean useAccessor,
            boolean useListener) {
        if (entityPropertyDescFactory == null) {
            throw new GenNullPointerException("entityPropertyDescFactory");
        }
        if (namingType == null) {
            throw new GenNullPointerException("namingType");
        }
        this.packageName = packageName;
        this.superclassName = superclassName;
        this.entityPropertyDescFactory = entityPropertyDescFactory;
        this.namingType = namingType;
        this.originalStatesPropertyName = originalStatesPropertyName;
        this.showCatalogName = showCatalogName;
        this.showSchemaName = showSchemaName;
        this.showTableName = showTableName;
        this.showDbComment = showDbComment;
        this.useAccessor = useAccessor;
        this.useListener = useListener;
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
        entityDesc.setOriginalStatesPropertyName(originalStatesPropertyName);
        entityDesc.setCatalogName(tableMeta.getCatalogName());
        entityDesc.setSchemaName(tableMeta.getSchemaName());
        entityDesc.setTableName(tableMeta.getName());
        entityDesc.setQualifiedTableName(tableMeta.getQualifiedTableName());
        entityDesc.setPackageName(packageName);
        handleSimpleName(entityDesc, tableMeta);
        if (superclassName != null) {
            entityDesc.setSuperclassSimpleName(ClassUtil
                    .getSimpleName(superclassName));
        }
        entityDesc.setListenerClassSimpleName(ClassUtil
                .getSimpleName(entityDesc.getSimpleName()
                        + Constants.ENTITY_LISTENER_SUFFIX));
        entityDesc.setCompositeId(tableMeta.hasCompositePrimaryKey());
        entityDesc.setComment(tableMeta.comment);
        entityDesc.setShowCatalogName(showCatalogName);
        entityDesc.setShowSchemaName(showSchemaName);
        entityDesc.setShowDbComment(true);
        entityDesc.setUseAccessor(useAccessor);
        entityDesc.setUseListener(useListener);
        entityDesc.setTemplateName(Constants.ENTITY_TEMPLATE);
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
        String entityName = entityDesc.getSimpleName();
        String tableName = entityDesc.getTableName();
        return !tableName.equalsIgnoreCase(namingType.apply(entityName));
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
        for (ColumnMeta columnMeta : tableMeta.getColumnMetas()) {
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
        classDescSupport.addImportName(entityDesc, ClassConstants.Entity);
        if (entityDesc.getCatalogName() != null
                || entityDesc.getSchemaName() != null
                || entityDesc.getTableName() != null) {
            classDescSupport.addImportName(entityDesc, ClassConstants.Table);
        }
        if (superclassName != null) {
            classDescSupport.addImportName(entityDesc, superclassName);
        }
        if (namingType != NamingType.NONE) {
            classDescSupport.addImportName(entityDesc, namingType
                    .getEnumConstant());
        }
        if (originalStatesPropertyName != null) {
            classDescSupport
                    .addImportName(entityDesc, ClassConstants.OriginalStates);
        }
        for (EntityPropertyDesc propertyDesc : entityDesc
                .getEntityPropertyDescs()) {
            if (propertyDesc.isId()) {
                classDescSupport.addImportName(entityDesc, ClassConstants.Id);
                if (propertyDesc.getGenerationType() != null) {
                    classDescSupport
                            .addImportName(entityDesc, ClassConstants.GeneratedValue);
                    classDescSupport
                            .addImportName(entityDesc, ClassConstants.GenerationType);
                    if (propertyDesc.getGenerationType() != null) {
                        classDescSupport.addImportName(entityDesc, propertyDesc
                                .getGenerationType().getEnumConstant());
                    }
                }
            }
            classDescSupport.addImportName(entityDesc, ClassConstants.Column);
            if (propertyDesc.isVersion()) {
                classDescSupport
                        .addImportName(entityDesc, ClassConstants.Version);
            }
            classDescSupport.addImportName(entityDesc, propertyDesc
                    .getPropertyClassName());
        }
    }

}
