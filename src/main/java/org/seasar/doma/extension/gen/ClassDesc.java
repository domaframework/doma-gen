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

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * クラスの記述です。
 * 
 * @author taedium
 * 
 */
public abstract class ClassDesc {

    /** インポート名のソートされたセット */
    protected final SortedSet<String> importNames = new TreeSet<String>();

    /** パッケージ名 */
    protected String packageName;

    /** クラスの単純名 */
    protected String simpleName;

    /** コメント */
    protected String comment;

    /**
     * インスタンスを構築します。
     */
    protected ClassDesc() {
    }

    /**
     * インポート名を追加します。
     * 
     * @param name
     *            インポート名
     */
    public void addImportName(String name) {
        importNames.add(name);
    }

    /**
     * インポート名のソートされたセットを返します。
     * 
     * @return インポート名のソートされたセット
     */
    public SortedSet<String> getImportNames() {
        return importNames;
    }

    /**
     * パッケージ名を設定します。
     * 
     * @param packageName
     *            パッケージ名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * パッケージ名を返します。
     * 
     * @return パッケージ名
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * クラスの単純名を設定します。
     * 
     * @param simpleName
     *            クラスの単純名
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /**
     * クラスの単純名を返します。
     * 
     * @return クラスの単純名
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * コメントを設定します。
     * 
     * @param comment
     *            コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * コメントを返します。
     * 
     * @return コメント
     */
    public String getComment() {
        return comment;
    }

    /**
     * 完全修飾名を返します。
     * 
     * @return 完全修飾名
     */
    public String getQualifiedName() {
        if (packageName == null || packageName.isEmpty()) {
            return simpleName;
        }
        return packageName + "." + simpleName;
    }
}
