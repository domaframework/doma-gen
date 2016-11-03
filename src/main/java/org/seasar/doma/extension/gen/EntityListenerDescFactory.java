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
 * エンティティ記述のファクトリです。
 * 
 * @author taedium
 */
public class EntityListenerDescFactory {

    /** パッケージ名 */
    protected final String packageName;

    /** スーパークラス名 */
    protected final String superclassName;

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     * 
     * @param packageName
     *            パッケージ名
     * @param superclassName
     *            スーパークラス名
     */
    public EntityListenerDescFactory(String packageName, String superclassName) {
        this.packageName = packageName;
        this.superclassName = superclassName;
    }

    /**
     * エンティティ記述を作成します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @return エンティティリスナー記述
     */
    public EntityListenerDesc createEntityListenerDesc(EntityDesc entityDesc) {
        EntityListenerDesc entityListenerDesc = new EntityListenerDesc();
        entityListenerDesc.setEntityDesc(entityDesc);
        entityListenerDesc.setPackageName(entityDesc.getPackageName());
        String entityPrefix = StringUtil.defaultString(entityDesc.getEntityPrefix(), "");
        String entitySuffix = StringUtil.defaultString(entityDesc.getEntitySuffix(), "");
        String entityName = entityPrefix + entityDesc.getSimpleName() + entitySuffix;
        entityListenerDesc.setSimpleName(entityName
                + Constants.ENTITY_LISTENER_SUFFIX);
        entityListenerDesc.setEntityClassSimpleName(entityDesc.getSimpleName());
        if (superclassName != null) {
            entityListenerDesc.setSuperclassSimpleName(ClassUtil
                    .getSimpleName(superclassName));
        }
        entityListenerDesc.setTemplateName(Constants.ENTITY_LISTENER_TEMPLATE);
        handleImportName(entityListenerDesc, entityDesc);
        return entityListenerDesc;
    }

    /**
     * /** インポート名を処理します。
     * 
     * @param entityListenerDesc
     *            エンティティリスナー記述
     * @param entityDesc
     *            エンティティ記述
     */
    protected void handleImportName(EntityListenerDesc entityListenerDesc,
            EntityDesc entityDesc) {
        classDescSupport.addImportName(entityListenerDesc, entityDesc
                .getQualifiedName());
        if (superclassName == null) {
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.EntityListener);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PreInsertContext);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PreUpdateContext);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PreDeleteContext);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PostInsertContext);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PostUpdateContext);
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.PostDeleteContext);
        } else {
            classDescSupport.addImportName(entityListenerDesc, superclassName);
        }
    }

}
