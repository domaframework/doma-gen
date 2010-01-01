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

/**
 * 列挙定数です。
 * 
 * @author taedium
 * 
 */
public enum EnumConstant {

    /** */
    NamingType_NONE(ClassConstant.NamingType, "NONE"),
    /** */
    NamingType_UPPER_CASE(ClassConstant.NamingType, "UPPER_CASE"),
    /** */
    NamingType_LOWER_CASE(ClassConstant.NamingType, "LOWER_CASE"),
    /** */
    NamingType_SNAKE_UPPER_CASE(ClassConstant.NamingType, "SNAKE_UPPER_CASE"),
    /** */
    NamingType_SNAKE_LOWER_CASE(ClassConstant.NamingType, "SNAKE_LOWER_CASE"),
    /** */
    GenerationType_SEQUENCE(ClassConstant.GenerationType, "SEQUENCE"),
    /** */
    GenerationType_TABLE(ClassConstant.GenerationType, "TABLE"),
    /** */
    GenerationType_IDENTITY(ClassConstant.GenerationType, "IDENTITY"), ;

    private final ClassConstant classConstant;

    private final String name;

    private EnumConstant(ClassConstant classConstant, String name) {
        this.classConstant = classConstant;
        this.name = name;
    }

    /**
     * クラス定数を返します。
     * 
     * @return クラス定数
     */
    public ClassConstant getClassConstant() {
        return classConstant;
    }

    /**
     * インポート名を返します。
     * 
     * @return インポート名
     */
    public String getImportName() {
        return classConstant.getQualifiedName();
    }

    /**
     * 参照名を返します。
     * 
     * @return 参照名
     */
    public String getReferenceName() {
        return classConstant.getSimpleName() + "." + name;
    }

    /**
     * 名前を返します。
     * 
     * @return 名前
     */
    public String getName() {
        return name;
    }
}
