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

/**
 * クラスの定数です。
 * 
 * @author taedium
 * 
 */
public enum ClassConstants {

    /** */
    Dao("org.seasar.doma", "Dao"),
    /** */
    Insert("org.seasar.doma", "Insert"),
    /** */
    Update("org.seasar.doma", "Update"),
    /** */
    Delete("org.seasar.doma", "Delete"),
    /** */
    Select("org.seasar.doma", "Select"),
    /** */
    Entity("org.seasar.doma", "Entity"),
    /** */
    Table("org.seasar.doma", "Table"),
    /** */
    Id("org.seasar.doma", "Id"),
    /** */
    GeneratedValue("org.seasar.doma", "GeneratedValue"),
    /** */
    GenerationType("org.seasar.doma", "GenerationType"),
    /** */
    SequenceGenerator("org.seasar.doma", "SequenceGenerator"),
    /** */
    TableGenerator("org.seasar.doma", "TableGenerator"),
    /** */
    Column("org.seasar.doma", "Column"),
    /** */
    Version("org.seasar.doma", "Version"),
    /** */
    OriginalStates("org.seasar.doma", "OriginalStates"),
    /** */
    NamingType("org.seasar.doma.jdbc.entity", "NamingType"),
    /** */
    EntityListener("org.seasar.doma.jdbc.entity", "EntityListener"),
    /** */
    PreInsertContext("org.seasar.doma.jdbc.entity", "PreInsertContext"),
    /** */
    PreUpdateContext("org.seasar.doma.jdbc.entity", "PreUpdateContext"),
    /** */
    PreDeleteContext("org.seasar.doma.jdbc.entity", "PreDeleteContext"),
    /** */
    PostInsertContext("org.seasar.doma.jdbc.entity", "PostInsertContext"),
    /** */
    PostUpdateContext("org.seasar.doma.jdbc.entity", "PostUpdateContext"),
    /** */
    PostDeleteContext("org.seasar.doma.jdbc.entity", "PostDeleteContext"),
    /** */
    StandardDialect("org.seasar.doma.jdbc.dialect", "StandardDialect"),
    /** */
    HsqldbDialect("org.seasar.doma.jdbc.dialect", "HsqldbDialect"),
    /** */
    H2Dialect("org.seasar.doma.jdbc.dialect", "H2Dialect"),
    /** */
    MysqlDialect("org.seasar.doma.jdbc.dialect", "MysqlDialect"),
    /** */
    PostgresDialect("org.seasar.doma.jdbc.dialect", "PostgresDialect"),
    /** */
    Db2Dialect("org.seasar.doma.jdbc.dialect", "Db2Dialect"),
    /** */
    Mssql2008("org.seasar.doma.jdbc.dialect", "Mssql2008Dialect"),
    /** */
    Mssql("org.seasar.doma.jdbc.dialect", "MssqlDialect"),
    /** */
    Oracle11Dialect("org.seasar.doma.jdbc.dialect", "Oracle11Dialect"),
    /** */
    OracleDialect("org.seasar.doma.jdbc.dialect", "OracleDialect"),
    /** @since 1.7.0 */
    Config("org.seasar.doma.jdbc", "Config"),
    /** */
    bytes("", "byte[]"), ;

    private final String packageName;

    private final String simpleName;

    private ClassConstants(String packageName, String simpleName) {
        this.packageName = packageName;
        this.simpleName = simpleName;
    }

    /**
     * 完全修飾名を返します。
     * 
     * @return 完全修飾名
     */
    public String getQualifiedName() {
        return packageName + "." + simpleName;
    }

    /**
     * パッケージ名を返します。
     * 
     * @return パッケージ名
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 単純名を返します。
     * 
     * @return 単純名
     */
    public String getSimpleName() {
        return simpleName;
    }

}