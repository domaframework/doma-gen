package org.seasar.doma.extension.gen;

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

/**
 * Daoデリゲート記述です。
 * 
 * @author taedium
 * @since 1.7.0
 */
public class DaoDelegateDesc extends ClassDesc {

    /** Dao記述 */
    protected DaoDesc daoDesc;

    /** テンプレート名 */
    protected String templateName;

    /**
     * Dao記述を返します。
     * 
     * @return Dao記述
     */
    public DaoDesc getDaoDesc() {
        return daoDesc;
    }

    /**
     * Dao記述を設定します。
     * 
     * @param daoDesc
     *            Dao記述
     */
    public void setDaoDesc(DaoDesc daoDesc) {
        this.daoDesc = daoDesc;
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