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
 * エンティティリスナー記述です。
 * 
 * @author taedium
 */
public class EntityListenerDesc extends ClassDesc {

    /** エンティティクラスの単純名 */
    protected String entityClassSimpleName;

    /** スーパークラスの単純名 */
    protected String superclassSimpleName;

    /** リスナークラスの単純名 */
    protected String listenerClassSimpleName;

    /** エンティティ記述 */
    protected EntityDesc entityDesc;

    /** テンプレート名 */
    protected String templateName;

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

    /**
     * スーパークラスの単純名を返します。
     * 
     * @return スーパークラスの単純名
     */
    public String getSuperclassSimpleName() {
        return superclassSimpleName;
    }

    /**
     * スーパークラスの単純名を設定します。
     * 
     * @param superclassSimpleName
     *            スーパークラスの単純名
     */
    public void setSuperclassSimpleName(String superclassSimpleName) {
        this.superclassSimpleName = superclassSimpleName;
    }

    /**
     * リスナークラスの単純名を返します。
     * 
     * @return リスナークラスの単純名
     */
    public String getListenerClassSimpleName() {
        return listenerClassSimpleName;
    }

    /**
     * リスナークラスの単純名を設定します。
     * 
     * @param listenerClassSimpleName
     *            リスナークラスの単純名
     */
    public void setListenerClassSimpleName(String listenerClassSimpleName) {
        this.listenerClassSimpleName = listenerClassSimpleName;
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
}