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
 * {@link DaoDelegateDesc} のファクトリです。
 * 
 * @author taedium
 * @since 1.7.0
 */
public class DaoDelegateDescFactory {

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     */
    public DaoDelegateDescFactory() {
    }

    /**
     * Daoデリゲート記述を作成します。
     * 
     * @param daoDesc
     *            Dao記述
     * @return Daoデリゲート記述
     */
    public DaoDelegateDesc createDaoDelegateDesc(DaoDesc daoDesc) {
        DaoDelegateDesc delegateDesc = new DaoDelegateDesc();
        delegateDesc.setPackageName(daoDesc.getPackageName());
        delegateDesc.setSimpleName(daoDesc.getSimpleName()
                + Constants.DAO_DELEGATE_SUFFIX);
        delegateDesc.setTemplateName(Constants.DAO_DELEGATE_TEMPLATE);
        delegateDesc.setDaoDesc(daoDesc);
        handleImportName(delegateDesc, daoDesc);
        return delegateDesc;
    }

    /**
     * インポート名を処理します。
     * 
     * @param delegateDesc
     *            Daoデリゲート記述
     * @param daoDesc
     *            Dao記述
     */
    protected void handleImportName(DaoDelegateDesc delegateDesc,
            DaoDesc daoDesc) {
        classDescSupport
                .addImportName(delegateDesc, daoDesc.getQualifiedName());
        classDescSupport.addImportName(delegateDesc, daoDesc.getEntityDesc()
                .getQualifiedName());
        classDescSupport.addImportName(delegateDesc, ClassConstants.Config);
    }

}
