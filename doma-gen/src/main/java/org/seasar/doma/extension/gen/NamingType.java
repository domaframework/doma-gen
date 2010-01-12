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
 * ネーミング規約を表します。
 * 
 * @author taedium
 * 
 */
public enum NamingType {

    /** */
    NONE(EnumConstant.NamingType_NONE, false),
    /** */
    LOWER_CASE(EnumConstant.NamingType_LOWER_CASE, false),
    /** */
    UPPER_CASE(EnumConstant.NamingType_UPPER_CASE, false),
    /** */
    SNAKE_UPPER_CASE(EnumConstant.NamingType_SNAKE_UPPER_CASE, true),
    /** */
    SNAKE_LOWER_CASE(EnumConstant.NamingType_SNAKE_LOWER_CASE, true);

    private final EnumConstant enumConstant;

    private final boolean snakeCase;

    private NamingType(EnumConstant enumConstant, boolean snakeCase) {
        this.enumConstant = enumConstant;
        this.snakeCase = snakeCase;
    }

    /**
     * 列挙定数を返します。
     * 
     * @return 列挙定数
     */
    public EnumConstant getEnumConstant() {
        return enumConstant;
    }

    /**
     * 参照名を返します。
     * 
     * @return 参照名
     */
    public String getReferenceName() {
        return enumConstant.getReferenceName();
    }

    /**
     * スネークケースの場合{@code true} を返します。
     * 
     * @return スネークケースの場合{@code true}
     */
    public boolean isSnakeCase() {
        return snakeCase;
    }

}
