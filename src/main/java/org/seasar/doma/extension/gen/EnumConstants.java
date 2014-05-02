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
 * 列挙定数です。
 * 
 * @author taedium
 * 
 */
public enum EnumConstants {

    /** */
    NamingType_NONE(ClassConstants.NamingType, "NONE"),
    /** */
    NamingType_UPPER_CASE(ClassConstants.NamingType, "UPPER_CASE"),
    /** */
    NamingType_LOWER_CASE(ClassConstants.NamingType, "LOWER_CASE"),
    /** */
    NamingType_SNAKE_UPPER_CASE(ClassConstants.NamingType, "SNAKE_UPPER_CASE"),
    /** */
    NamingType_SNAKE_LOWER_CASE(ClassConstants.NamingType, "SNAKE_LOWER_CASE"),
    /** */
    GenerationType_SEQUENCE(ClassConstants.GenerationType, "SEQUENCE"),
    /** */
    GenerationType_TABLE(ClassConstants.GenerationType, "TABLE"),
    /** */
    GenerationType_IDENTITY(ClassConstants.GenerationType, "IDENTITY"), ;

    private final ClassConstants classConstant;

    private final String name;

    private EnumConstants(ClassConstants classConstant, String name) {
        this.classConstant = classConstant;
        this.name = name;
    }

    /**
     * クラス定数を返します。
     * 
     * @return クラス定数
     */
    public ClassConstants getClassConstant() {
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
