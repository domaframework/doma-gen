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
 * SQL記述です。
 * 
 * @author taedium
 * 
 */
public class SqlDesc {

    /** ファイル名 */
    protected String fileName;

    /** テンプレート名 */
    protected String templateName;

    /** エンティティ記述 */
    protected EntityDesc entityDesc;

    /**
     *ファイル名 を返します。
     * 
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定します。
     * 
     * @param fileName
     *            ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
     * テンプレート名を設定します。
     * 
     * @param fileName
     *            テンプレート名
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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
     * @param fileName
     *            エンティティ記述
     */
    public void setEntityDesc(EntityDesc entityDesc) {
        this.entityDesc = entityDesc;
    }

}
