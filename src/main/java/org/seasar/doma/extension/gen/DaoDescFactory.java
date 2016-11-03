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

import org.seasar.doma.extension.gen.internal.util.ClassUtil;
import org.seasar.doma.extension.gen.internal.util.StringUtil;

/**
 * {@link DaoDesc} のファクトリです。
 * 
 * @author taedium
 */
public class DaoDescFactory {

    /** パッケージ名 */
    protected final String packageName;

    /** サフィックス */
    protected final String suffix;

    /** 設定クラス名 */
    protected final String configClassName;

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     * 
     * @param packageName
     *            パッケージ名
     * @param suffix
     *            サフィックス
     * @param configClassName
     *            設定クラス名、指定しない場合 {@code null}
     */
    public DaoDescFactory(String packageName, String suffix,
            String configClassName) {
        if (suffix == null) {
            throw new GenNullPointerException("suffix");
        }
        this.packageName = packageName;
        this.suffix = suffix;
        this.configClassName = configClassName;
    }

    /**
     * Dao記述を作成します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @return Dao記述
     */
    public DaoDesc createDaoDesc(EntityDesc entityDesc) {
        DaoDesc daoDesc = new DaoDesc();
        daoDesc.setPackageName(packageName);
        String entityPrefix = StringUtil.defaultString(entityDesc.getEntityPrefix(), "");
        String entitySuffix = StringUtil.defaultString(entityDesc.getEntitySuffix(), "");
        String simpleName = entityPrefix + entityDesc.getSimpleName() + entitySuffix + suffix;
        daoDesc.setSimpleName(simpleName);
        if (configClassName != null) {
            daoDesc.setConfigClassSimpleName(ClassUtil
                    .getSimpleName(configClassName));
        }
        daoDesc.setEntityDesc(entityDesc);
        daoDesc.setTemplateName(Constants.DAO_TEMPLATE);
        handleImportName(daoDesc, entityDesc);
        return daoDesc;
    }

    /**
     * インポート名を処理します。
     * 
     * @param daoDesc
     *            Dao記述
     * @param entityDesc
     *            エンティティ記述
     */
    protected void handleImportName(DaoDesc daoDesc, EntityDesc entityDesc) {
        classDescSupport.addImportName(daoDesc, ClassConstants.Dao);
        classDescSupport.addImportName(daoDesc, ClassConstants.Insert);
        classDescSupport.addImportName(daoDesc, ClassConstants.Update);
        classDescSupport.addImportName(daoDesc, ClassConstants.Delete);
        if (configClassName != null) {
            classDescSupport.addImportName(daoDesc, configClassName);
        }
        classDescSupport.addImportName(daoDesc, entityDesc.getQualifiedName());
        for (EntityPropertyDesc propertyDesc : entityDesc
                .getIdEntityPropertyDescs()) {
            classDescSupport.addImportName(daoDesc, propertyDesc
                    .getPropertyClassName());
            classDescSupport.addImportName(daoDesc, ClassConstants.Select);
        }
        if (entityDesc.getIdEntityPropertyDescs().size() > 0) {
            classDescSupport.addImportName(daoDesc, ClassConstants.Select);
            for (EntityPropertyDesc propertyDesc : entityDesc
                    .getIdEntityPropertyDescs()) {
                classDescSupport.addImportName(daoDesc, propertyDesc
                        .getPropertyClassName());
            }
            if (entityDesc.getVersionEntityPropertyDesc() != null) {
                classDescSupport.addImportName(daoDesc, entityDesc
                        .getVersionEntityPropertyDesc().getPropertyClassName());
            }
        }
    }

}
