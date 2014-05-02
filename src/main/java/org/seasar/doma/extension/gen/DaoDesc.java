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
 * Dao記述です。
 * 
 * @author taedium
 */
public class DaoDesc extends ClassDesc {

    /** 設定クラスの単純名 */
    protected String configClassSimpleName;

    /** エンティティ記述 */
    protected EntityDesc entityDesc;

    /** テンプレート名 */
    protected String templateName;

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
     * エンティティ記述を返します。
     * 
     * @return エンティティ記述
     */
    public EntityDesc getEntityDesc() {
        return entityDesc;
    }

    /**
     * エンティティ記述を設定します。
     * 
     * @param entityDesc
     *            エンティティ記述
     */
    public void setEntityDesc(EntityDesc entityDesc) {
        this.entityDesc = entityDesc;
    }

    /**
     * テンプレート名を設定します。
     * 
     * @param templateName
     *            テンプレート名
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * テンプレート名を返します。
     * 
     * @return テンプレート名
     */
    public String getTemplateName() {
        return templateName;
    }

}