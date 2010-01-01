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
 * Dao記述です。
 * 
 * @author taedium
 */
public class DaoDesc extends ClassDesc {

    /** 設定クラスの単純名 */
    protected String configClassSimpleName;

    /** エンティティクラスの単純名 */
    protected String entityClassSimpleName;

    /**
     * 設定クラスの単純名を返します。
     * 
     * @return 設定クラスの単純名
     */
    public String getConfigClassSimpleName() {
        return configClassSimpleName;
    }

    /**
     * 設定クラスの単純名を設定します。
     * 
     * @param configClassSimpleName
     *            設定クラスの単純名
     */
    public void setConfigClassSimpleName(String configClassSimpleName) {
        this.configClassSimpleName = configClassSimpleName;
    }

    /**
     * エンティティクラスの単純名を返します。
     * 
     * @return エンティティクラスの単純名
     */
    public String getEntityClassSimpleName() {
        return entityClassSimpleName;
    }

    /**
     * エンティティクラスの単純名を設定します。
     * 
     * @param entityClassSimpleName
     *            エンティティクラスの単純名
     */
    public void setEntityClassSimpleName(String entityClassSimpleName) {
        this.entityClassSimpleName = entityClassSimpleName;
    }

}