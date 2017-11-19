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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.Id;
import org.seasar.doma.SequenceGenerator;
import org.seasar.doma.TableGenerator;
import org.seasar.doma.Transient;
import org.seasar.doma.Version;
import org.seasar.doma.extension.gen.internal.message.Message;
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

    /** スーパークラス */
    protected final Class<?> superclass;

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     * 
     * @param packageName
     *            パッケージ名
     * @param superclass
     *            スーパークラス
     * @param entityPropertyDescFactory
     *            エンティティプロパティ記述のファクトリ
     * @param namingType
     *            ネーミング規約
     * @param originalStatesPropertyName
     *            オリジナルの状態を表すプロパティの名前
     * @param showCatalogName
     *            カタログ名を表示する場合 {@code true}
     * @param showSchemaName
     *            スキーマ名を表示する場合 {@code true}
     * @param showTableName
     *            テーブル名を表示する場合 {@code true}
     * @param showDbComment
     *            コメントを表示する場合 {@code true}
     * @param useAccessor
     *            アクセッサーを使用する場合 {@code true}
     * @param useListener
     *            エンティティリスナーを使用する場合 {@code true}
     */
    public EntityDescFactory(String packageName, Class<?> superclass,
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
        this.superclass = superclass;
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
        String name = StringUtil.fromSnakeCaseToCamelCase(tableMeta.getName());
        return createEntityDesc(tableMeta, null, null, StringUtil.capitalize(name));
    }

    /**
     * エンティティ記述を作成します。
     *
     * @param tableMeta
     *            テーブルメタデータ
     * @param entityPrefix
     *            エンティティクラスのプリフィックス
     * @param entitySuffix
     *            エンティティクラスのサフィックス
     * @return エンティティ記述
     */
    public EntityDesc createEntityDesc(TableMeta tableMeta,
                                       String entityPrefix, String entitySuffix) {
        String name = StringUtil.fromSnakeCaseToCamelCase(tableMeta.getName());
        return createEntityDesc(tableMeta, entityPrefix, entitySuffix,
                StringUtil.capitalize(name));
    }

    /**
     * エンティティの名前を指定してエンティティ記述を作成します。
     * 
     * @param tableMeta
     *            テーブルメタデータ
     * @param entityPrefix
     *            エンティティクラスのプリフィックス
     * @param entitySuffix
     *            エンティティクラスのサフィックス
     * @param simpleName
     *            エンティティ名
     * @return エンティティ記述
     */
    public EntityDesc createEntityDesc(TableMeta tableMeta,
                                       String entityPrefix, String entitySuffix,
                                       String simpleName) {
        EntityDesc entityDesc = new EntityDesc();
        entityDesc.setNamingType(namingType);
        entityDesc.setOriginalStatesPropertyName(originalStatesPropertyName);
        entityDesc.setCatalogName(tableMeta.getCatalogName());
        entityDesc.setSchemaName(tableMeta.getSchemaName());
        entityDesc.setTableName(tableMeta.getName());
        entityDesc.setQualifiedTableName(tableMeta.getQualifiedTableName());
        entityDesc.setPackageName(packageName);
        entityDesc.setEntityPrefix(StringUtil.defaultString(entityPrefix, ""));
        entityDesc.setEntitySuffix(StringUtil.defaultString(entitySuffix, ""));
        entityDesc.setSimpleName(simpleName);
        if (superclass != null) {
            entityDesc.setSuperclassSimpleName(superclass.getSimpleName());
        }
        entityDesc.setListenerClassSimpleName(entityDesc.getEntityPrefix()
                + ClassUtil.getSimpleName(entityDesc.getSimpleName()
                        + entityDesc.getEntitySuffix()
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
        String entityPrefix = StringUtil.defaultString(entityDesc.getEntityPrefix(), "");
        String entitySuffix = StringUtil.defaultString(entityDesc.getEntitySuffix(), "");
        String entityName = entityPrefix + entityDesc.getSimpleName() + entitySuffix;
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
        Map<String, EntityPropertyDesc> propertyDescMap = new LinkedHashMap<String, EntityPropertyDesc>();
        for (ColumnMeta columnMeta : tableMeta.getColumnMetas()) {
            EntityPropertyDesc propertyDesc = entityPropertyDescFactory
                    .createEntityPropertyDesc(entityDesc, columnMeta);
            propertyDescMap.put(propertyDesc.getColumnName().toLowerCase(),
                    propertyDesc);
        }
        for (EntityPropertyInfo propertyInfo : getSuperclassEntityPropertyInfo()) {
            EntityPropertyDesc propertyDesc = propertyDescMap
                    .get(propertyInfo.columnName.toLowerCase());
            if (propertyDesc == null) {
                throw new GenException(Message.DOMAGEN0021,
                        superclass.getName(),
                        propertyInfo.propertyField.getName(),
                        propertyInfo.columnName,
                        entityDesc.getQualifiedTableName());
            }
            mergeEntityProperty(propertyDesc, propertyInfo);
        }
        for (EntityPropertyDesc propertyDesc : propertyDescMap.values()) {
            entityDesc.addEntityPropertyDesc(propertyDesc);
        }
    }

    /**
     * エンティティプロパティ記述にエンティティプロパティ情報をマージします。
     * 
     * @param dest
     *            エンティティプロパティ記述
     * @param src
     *            エンティティプロパティ情報
     * @since 1.7.0
     */
    protected void mergeEntityProperty(EntityPropertyDesc dest,
            EntityPropertyInfo src) {
        dest.setEntityClassName(src.entityClass.getName());
        dest.setPropertyClassName(src.propertyField.getType().getName());
        Class<?> maybeNumberClass = ClassUtil
                .toBoxedPrimitiveTypeIfPossible(src.propertyField.getType());
        if (Number.class.isAssignableFrom(maybeNumberClass)) {
            dest.setNumber(true);
        }
        if (src.version != null) {
            dest.setVersion(true);
        }
        if (src.id != null) {
            dest.setId(true);
            if (src.generatedValue != null) {
                switch (src.generatedValue.strategy()) {
                case IDENTITY:
                    dest.setGenerationType(GenerationType.IDENTITY);
                    break;
                case SEQUENCE:
                    dest.setGenerationType(GenerationType.SEQUENCE);
                    if (src.sequenceGenerator != null) {
                        dest.setInitialValue(src.sequenceGenerator
                                .initialValue());
                        dest.setAllocationSize(src.sequenceGenerator
                                .allocationSize());
                        dest.setAllocationSize(src.sequenceGenerator
                                .allocationSize());
                    }
                    break;
                case TABLE:
                    dest.setGenerationType(GenerationType.TABLE);
                    if (src.tableGenerator != null) {
                        dest.setInitialValue(src.tableGenerator.initialValue());
                        dest.setAllocationSize(src.tableGenerator
                                .allocationSize());
                        dest.setAllocationSize(src.tableGenerator
                                .allocationSize());
                    }
                    break;
                default:
                    break;
                }
            }
        }
    }

    /**
     * スーパークラスに定義されたエンティティプロパティの情報のセットを返します。
     * 
     * @return スーパークラスに定義されたエンティティプロパティの情報のセット
     * @since 1.7.0
     */
    protected Set<EntityPropertyInfo> getSuperclassEntityPropertyInfo() {
        Set<EntityPropertyInfo> results = new HashSet<EntityPropertyInfo>();
        if (superclass == null) {
            return results;
        }
        for (Class<?> clazz = superclass; clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            Entity entity = clazz.getAnnotation(Entity.class);
            if (entity == null) {
                continue;
            }
            org.seasar.doma.jdbc.entity.NamingType namingType = entity.naming();
            for (Field field : clazz.getDeclaredFields()) {
                int m = field.getModifiers();
                if (Modifier.isStatic(m)) {
                    continue;
                }
                if (field.isAnnotationPresent(Transient.class)) {
                    continue;
                }
                EntityPropertyInfo propertyInfo = new EntityPropertyInfo();
                propertyInfo.entityClass = clazz;
                propertyInfo.propertyField = field;
                Column column = field.getAnnotation(Column.class);
                propertyInfo.column = column;
                if (column == null || column.name().isEmpty()) {
                    propertyInfo.columnName = namingType.apply(field.getName())
                            .toLowerCase();
                } else {
                    propertyInfo.columnName = column.name();
                }
                propertyInfo.id = field.getAnnotation(Id.class);
                propertyInfo.generatedValue = field
                        .getAnnotation(GeneratedValue.class);
                propertyInfo.sequenceGenerator = field
                        .getAnnotation(SequenceGenerator.class);
                propertyInfo.tableGenerator = field
                        .getAnnotation(TableGenerator.class);
                propertyInfo.version = field.getAnnotation(Version.class);
                results.add(propertyInfo);
            }
        }
        return results;
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
        if (superclass != null) {
            classDescSupport.addImportName(entityDesc, superclass.getName());
        }
        if (namingType != NamingType.NONE) {
            classDescSupport.addImportName(entityDesc,
                    namingType.getEnumConstant());
        }
        if (originalStatesPropertyName != null) {
            classDescSupport.addImportName(entityDesc,
                    ClassConstants.OriginalStates);
        }
        for (EntityPropertyDesc propertyDesc : entityDesc
                .getOwnEntityPropertyDescs()) {
            if (propertyDesc.isId()) {
                classDescSupport.addImportName(entityDesc, ClassConstants.Id);
                if (propertyDesc.getGenerationType() != null) {
                    classDescSupport.addImportName(entityDesc,
                            ClassConstants.GeneratedValue);
                    classDescSupport.addImportName(entityDesc,
                            ClassConstants.GenerationType);
                    GenerationType generationType = propertyDesc
                            .getGenerationType();
                    if (generationType != null) {
                        classDescSupport.addImportName(entityDesc,
                                generationType.getEnumConstant());
                        if (generationType == GenerationType.SEQUENCE) {
                            classDescSupport.addImportName(entityDesc,
                                    ClassConstants.SequenceGenerator);
                        } else if (generationType == GenerationType.TABLE) {
                            classDescSupport.addImportName(entityDesc,
                                    ClassConstants.TableGenerator);
                        }
                    }
                }
            }
            classDescSupport.addImportName(entityDesc, ClassConstants.Column);
            if (propertyDesc.isVersion()) {
                classDescSupport.addImportName(entityDesc,
                        ClassConstants.Version);
            }
            classDescSupport.addImportName(entityDesc,
                    propertyDesc.getPropertyClassName());
        }
    }

    /**
     * エンティティプロパティ情報です。
     * <p>
     * データベースではなくエンティティクラスから得られた情報です。
     * 
     * @author taedium
     * @since 1.7.0
     */
    protected static class EntityPropertyInfo {

        protected String columnName;

        protected Class<?> entityClass;

        protected Field propertyField;

        protected Column column;

        protected Id id;

        protected GeneratedValue generatedValue;

        protected SequenceGenerator sequenceGenerator;

        protected TableGenerator tableGenerator;

        protected Version version;

    }
}
