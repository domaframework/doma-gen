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

import org.seasar.doma.extension.gen.internal.util.ClassUtil;

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
     * @param tableMeta
     *            テーブルメタデータ
     * @return エンティティ記述
     */
    public EntityListenerDesc createEntityListenerDesc(EntityDesc entityDesc) {
        EntityListenerDesc entityListenerDesc = new EntityListenerDesc();
        entityListenerDesc.setPackageName(entityDesc.getPackageName());
        entityListenerDesc.setSimpleName(entityDesc.getSimpleName()
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
     * @param entityDesc
     *            エンティティ記述
     * @param tableMeta
     *            テーブルメタデータ
     */
    protected void handleImportName(EntityListenerDesc entityListenerDesc,
            EntityDesc entityDesc) {
        classDescSupport.addImportName(entityListenerDesc, entityDesc
                .getQualifiedName());
        if (superclassName == null) {
            classDescSupport
                    .addImportName(entityListenerDesc, ClassConstants.EntityListener);
        } else {
            classDescSupport.addImportName(entityListenerDesc, superclassName);
        }
    }

}
