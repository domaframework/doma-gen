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

/**
 * {@link ClassDesc} のサポートクラスです。
 * 
 * @author taedium
 */
public class ClassDescSupport {

    /**
     * インポート名を追加します。
     * 
     * @param classDesc
     *            クラス記述
     * @param importedClass
     *            インポートされるクラス
     */
    public void addImportName(ClassDesc classDesc, Class<?> importedClass) {
        String canonicalName = importedClass.getCanonicalName();
        String packageName = ClassUtil.getPackageName(canonicalName);
        if (isImportTargetPackage(classDesc, packageName)) {
            classDesc.addImportName(canonicalName);
        }
    }

    /**
     * インポート名を追加します。
     * 
     * @param classDesc
     *            クラス記述
     * @param importedClassName
     *            インポートされるクラスの名前
     */
    public void addImportName(ClassDesc classDesc, String importedClassName) {
        String packageName = ClassUtil.getPackageName(importedClassName);
        if (isImportTargetPackage(classDesc, packageName)) {
            classDesc.addImportName(importedClassName);
        }
    }

    /**
     * インポート名を追加します。
     * 
     * @param classDesc
     *            クラス記述
     * @param importedClass
     *            インポートされるクラス
     */
    public void addImportName(ClassDesc classDesc, ClassConstants importedClass) {
        String packageName = importedClass.getPackageName();
        if (isImportTargetPackage(classDesc, packageName)) {
            classDesc.addImportName(importedClass.getQualifiedName());
        }
    }

    /**
     * インポート名を追加します。
     * 
     * @param classDesc
     *            クラス記述
     * @param importedClass
     *            インポートされるクラス
     */
    public void addImportName(ClassDesc classDesc, EnumConstants importedClass) {
        ClassConstants classConstant = importedClass.getClassConstant();
        String packageName = classConstant.getPackageName();
        if (isImportTargetPackage(classDesc, packageName)) {
            classDesc.addImportName(importedClass.getImportName());
        }
    }

    /**
     * インポート対象のパッケージの場合 {@code true} を返します。
     * 
     * @param classDesc
     *            クラス記述
     * @param importPackageName
     *            インポートするパッケージ名
     * @return インポート対象のパッケージの場合 {@code true}
     */
    protected boolean isImportTargetPackage(ClassDesc classDesc,
            String importPackageName) {
        if (importPackageName == null) {
            return false;
        }
        if (importPackageName.isEmpty()) {
            return false;
        }
        if (importPackageName.equals(classDesc.getPackageName())) {
            return false;
        }
        if (importPackageName.equals("java.lang")) {
            return false;
        }
        return true;
    }

}
