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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Pattern;

import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.StringUtil;

/**
 * エンティティプロパティ記述のファクトリです。
 * 
 * @author taedium
 */
public class EntityPropertyDescFactory {

    /** 方言 */
    protected final GenDialect dialect;

    /** ドメインクラス名のリゾルバ */
    protected final EntityPropertyClassNameResolver propertyClassNameResolver;

    /** バージョンカラム名パターン */
    protected final Pattern versionColumnNamePattern;

    /** 識別子を生成する方法 */
    protected final GenerationType generationType;

    /** 識別子の初期値 */
    protected final Long initialValue;

    /** 識別子の割り当てサイズ */
    protected final Long allocationSize;

    /** カラム名を表示する場合 {@code true} */
    protected final boolean showColumnName;

    /**
     * インスタンスを構築します。
     * 
     * @param dialect
     *            方言
     * @param propertyClassNameResolver
     *            プロパティクラス名のリゾルバ
     * @param versionColumnNamePattern
     *            バージョンカラム名パターン
     * @param generationType
     *            識別子を生成する方法
     * @param initialValue
     *            識別子の初期値
     * @param allocationSize
     *            識別子の割り当てサイズ
     * @param showColumnName
     *            カラム名を表示する場合 {@code true}
     */
    public EntityPropertyDescFactory(GenDialect dialect,
            EntityPropertyClassNameResolver propertyClassNameResolver,
            String versionColumnNamePattern, GenerationType generationType,
            Long initialValue, Long allocationSize, boolean showColumnName) {
        if (dialect == null) {
            throw new GenNullPointerException("dialect");
        }
        if (propertyClassNameResolver == null) {
            throw new GenNullPointerException("propertyClassNameResolver");
        }
        if (versionColumnNamePattern == null) {
            throw new GenNullPointerException("versionColumnNamePattern");
        }
        this.dialect = dialect;
        this.propertyClassNameResolver = propertyClassNameResolver;
        this.versionColumnNamePattern = Pattern.compile(
                versionColumnNamePattern, Pattern.CASE_INSENSITIVE);
        this.generationType = generationType;
        this.initialValue = initialValue;
        this.allocationSize = allocationSize;
        this.showColumnName = showColumnName;

        validateGenerationType(generationType);
    }

    /**
     * 識別子を生成する方法を検証します。
     * 
     * @param generationType
     *            識別子を生成する方法
     */
    protected void validateGenerationType(GenerationType generationType) {
        if (generationType == null) {
            return;
        }
        if (generationType == GenerationType.IDENTITY) {
            if (!dialect.supportsIdentity()) {
                throw new GenException(Message.DOMAGEN0003, dialect.getName());
            }
        } else if (generationType == GenerationType.SEQUENCE) {
            if (!dialect.supportsSequence()) {
                throw new GenException(Message.DOMAGEN0004, dialect.getName());
            }
        }
    }

    /**
     * エンティティプロパティ記述を作成します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param columnMeta
     *            カラムメタデータ
     * @return エンティティプロパティ記述
     */
    public EntityPropertyDesc createEntityPropertyDesc(EntityDesc entityDesc,
            ColumnMeta columnMeta) {
        EntityPropertyDesc propertyDesc = new EntityPropertyDesc();
        handleName(entityDesc, propertyDesc, columnMeta);
        propertyDesc.setColumnName(columnMeta.getName());
        if (columnMeta.isPrimaryKey()) {
            propertyDesc.setId(true);
            if (!entityDesc.isCompositeId()) {
                if (columnMeta.isAutoIncrement()) {
                    propertyDesc.setGenerationType(GenerationType.IDENTITY);
                } else {
                    propertyDesc.setGenerationType(generationType);
                    propertyDesc.setInitialValue(initialValue);
                    propertyDesc.setAllocationSize(allocationSize);
                }
            }
        }
        propertyDesc.setComment(columnMeta.getComment());
        handlePropertyClassName(entityDesc, propertyDesc, columnMeta);
        descriminateType(entityDesc, propertyDesc, columnMeta);
        handleShowColumnName(entityDesc, propertyDesc, columnMeta);
        handleVersion(entityDesc, propertyDesc, columnMeta);
        propertyDesc.setEntityClassName(entityDesc.getQualifiedName());
        return propertyDesc;
    }

    /**
     * 名前を処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @param columnMeta
     *            カラムメタデータ
     */
    protected void handleName(EntityDesc entityDesc,
            EntityPropertyDesc propertyDesc, ColumnMeta columnMeta) {
        String name = StringUtil.fromSnakeCaseToCamelCase(columnMeta.getName());
        propertyDesc.setName(name);
    }

    /**
     * プロパティクラス名を処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @param columnMeta
     *            カラムメタデータ
     */
    protected void handlePropertyClassName(EntityDesc entityDesc,
            EntityPropertyDesc propertyDesc, ColumnMeta columnMeta) {
        String defaultClassName = dialect
                .getMappedPropertyClassName(columnMeta);
        String className = propertyClassNameResolver.resolve(entityDesc,
                propertyDesc.getName(), defaultClassName);
        if (className == null) {
            Logger.info(Message.DOMAGEN0018.getMessage(columnMeta
                    .getTableMeta().getName(), columnMeta.getName(), columnMeta
                    .getTypeName(), columnMeta.getSqlType()));
            className = String.class.getName();
        }
        propertyDesc.setPropertyClassName(className);
    }

    /**
     * 数値かどうかを処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @param columnMeta
     *            カラムメタデータ
     * @since 1.11.0
     */
    protected void descriminateType(EntityDesc entityDesc,
            EntityPropertyDesc propertyDesc, ColumnMeta columnMeta) {
        String defaultClassName = dialect
                .getMappedPropertyClassName(columnMeta);
        if (Byte.class.getName().equals(defaultClassName)
                || Short.class.getName().equals(defaultClassName)
                || Integer.class.getName().equals(defaultClassName)
                || Long.class.getName().equals(defaultClassName)
                || Float.class.getName().equals(defaultClassName)
                || Double.class.getName().equals(defaultClassName)
                || BigInteger.class.getName().equals(defaultClassName)
                || BigDecimal.class.getName().equals(defaultClassName)
                || byte.class.getName().equals(defaultClassName)
                || short.class.getName().equals(defaultClassName)
                || int.class.getName().equals(defaultClassName)
                || long.class.getName().equals(defaultClassName)
                || float.class.getName().equals(defaultClassName)
                || double.class.getName().equals(defaultClassName)) {
            propertyDesc.setNumber(true);
        } else if (Time.class.getName().equals(defaultClassName)
                || LocalTime.class.getName().equals(defaultClassName)) {
            propertyDesc.setTime(true);
        } else if (Date.class.getName().equals(defaultClassName)
                || LocalDate.class.getName().equals(defaultClassName)) {
            propertyDesc.setDate(true);
        } else if (Timestamp.class.getName().equals(defaultClassName)
                || java.util.Date.class.getName().equals(defaultClassName)
                || LocalDateTime.class.getName().equals(defaultClassName)) {
            propertyDesc.setTimestamp(true);
        }
    }

    /**
     * カラム名を表示するかどうかを処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @param columnMeta
     *            カラムメタデータ
     */
    protected void handleShowColumnName(EntityDesc entityDesc,
            EntityPropertyDesc propertyDesc, ColumnMeta columnMeta) {
        if (showColumnName
                || isNameDifferentBetweenPropertyAndColumn(entityDesc,
                        propertyDesc)) {
            propertyDesc.setShowColumnName(true);
        }
    }

    /**
     * プロパティ名とカラム名が異なる場合 {@code true}を返します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @return プロパティ名とカラム名が異なる場合 {@code true}
     */
    protected boolean isNameDifferentBetweenPropertyAndColumn(
            EntityDesc entityDesc, EntityPropertyDesc propertyDesc) {
        String propertyName = propertyDesc.getName();
        String columnName = propertyDesc.getColumnName();
        NamingType namingType = entityDesc.getNamingType();
        return !columnName.equalsIgnoreCase(namingType.apply(propertyName));
    }

    /**
     * バージョンを処理します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyDesc
     *            エンティティプロパティ記述
     * @param columnMeta
     *            カラムメタデータ
     */
    protected void handleVersion(EntityDesc entityDesc,
            EntityPropertyDesc propertyDesc, ColumnMeta columnMeta) {
        if (isVersionAnnotatable(propertyDesc.getPropertyClassName())) {
            if (versionColumnNamePattern.matcher(columnMeta.getName())
                    .matches()) {
                propertyDesc.setVersion(true);
            }
        }
    }

    /**
     * {@code org.seasar.doma.Version} を注釈可能なクラス名の場合 {@code true} を返します。
     * 
     * @param className
     *            クラス名
     * @return {@code Version} を注釈可能なクラス名の場合 {@code true}
     */
    protected boolean isVersionAnnotatable(String className) {
        return Byte.class.getName().equals(className)
                || Short.class.getName().equals(className)
                || Integer.class.getName().equals(className)
                || Long.class.getName().equals(className)
                || Float.class.getName().equals(className)
                || Double.class.getName().equals(className)
                || BigInteger.class.getName().equals(className)
                || BigDecimal.class.getName().equals(className);
    }

}
