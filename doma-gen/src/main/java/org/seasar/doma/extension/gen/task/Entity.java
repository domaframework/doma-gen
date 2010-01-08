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
package org.seasar.doma.extension.gen.task;

import java.io.File;

import org.apache.tools.ant.types.DataType;

/**
 * @author taedium
 * 
 */
public class Entity extends DataType {

    protected boolean generate = true;

    /** 生成されるJavaファイルと同名のファイルが存在する際に上書きする場合{@code true}、しない場合{@code false} */
    protected boolean overwrite = true;

    /** このタスクで対象とするエンティティクラスに共通のスーパークラスの名前、指定しない場合は {@code null} **/
    protected String superclassName = null;

    /** このタスクで対象とするエンティティクラスに共通のエンティティリスナーの名前、指定しない場合は {@code null} **/
    protected String listenerClassName = null;

    /** エンティティクラスのパッケージ名 */
    protected String packageName = "example.entity";

    /** 識別子の生成方法、指定しない場合は {@code null} */
    protected GenerationTypeAttribute generationType = null;

    /** ネーミング規約、指定しない場合は {@code null} */
    protected NamingTypeAttribute namingType = null;

    /** 識別子の初期値、指定しない場合は {@code null} */
    protected Long initialValue = null;

    /** 識別子の割り当てサイズ、指定しない場合は {@code null} */
    protected Long allocationSize = null;

    /** {@code org.seasar.doma.Table#catalog()} でカタログ名を表示する場合 {@code true} */
    protected boolean showCatalogName = false;

    /** {@code org.seasar.doma.Table#schema()} でスキーマ名を表示する場合 {@code true} */
    protected boolean showSchemaName = false;

    /** {@code org.seasar.doma.Table#name()} でテーブル名を表示する場合 {@code true} */
    protected boolean showTableName = true;

    /** {@code org.seasar.doma.Column#name()} でカラム名を表示する場合 {@code true} */
    protected boolean showColumnName = true;

    /** エンティティクラスのJavadocコメントでデータベースのコメントを表示する場合 {@code true} */
    protected boolean showDbComment = true;

    /** エンティティクラスでアクセッサーを使用する場合 {@code true} */
    protected boolean useAccessor = true;

    /** エンティティプロパティ名の正規表現をキー、クラス名を値とするプロパティファイル */
    protected File entityPropertyClassNamesFile = null;

    /** 生成されるJavaファイルの出力先ディレクトリ */
    protected File destDir = null;

    /** Javaファイルのエンコーディング */
    protected String encoding = "UTF-8";

    public boolean isGenerate() {
        return generate;
    }

    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getSuperclassName() {
        return superclassName;
    }

    public void setSuperclassName(String superclassName) {
        this.superclassName = superclassName;
    }

    public String getListenerClassName() {
        return listenerClassName;
    }

    public void setListenerClassName(String listenerClassName) {
        this.listenerClassName = listenerClassName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public GenerationTypeAttribute getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationTypeAttribute generationType) {
        this.generationType = generationType;
    }

    public NamingTypeAttribute getNamingType() {
        return namingType;
    }

    public void setNamingType(NamingTypeAttribute namingType) {
        this.namingType = namingType;
    }

    public Long getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Long initialValue) {
        this.initialValue = initialValue;
    }

    public Long getAllocationSize() {
        return allocationSize;
    }

    public void setAllocationSize(Long allocationSize) {
        this.allocationSize = allocationSize;
    }

    public boolean isShowCatalogName() {
        return showCatalogName;
    }

    public void setShowCatalogName(boolean showCatalogName) {
        this.showCatalogName = showCatalogName;
    }

    public boolean isShowSchemaName() {
        return showSchemaName;
    }

    public void setShowSchemaName(boolean showSchemaName) {
        this.showSchemaName = showSchemaName;
    }

    public boolean isShowTableName() {
        return showTableName;
    }

    public void setShowTableName(boolean showTableName) {
        this.showTableName = showTableName;
    }

    public boolean isShowColumnName() {
        return showColumnName;
    }

    public void setShowColumnName(boolean showColumnName) {
        this.showColumnName = showColumnName;
    }

    public boolean isShowDbComment() {
        return showDbComment;
    }

    public void setShowDbComment(boolean showDbComment) {
        this.showDbComment = showDbComment;
    }

    public boolean isUseAccessor() {
        return useAccessor;
    }

    public void setUseAccessor(boolean useAccessor) {
        this.useAccessor = useAccessor;
    }

    public File getEntityPropertyClassNamesFile() {
        return entityPropertyClassNamesFile;
    }

    public void setEntityPropertyClassNamesFile(
            File entityPropertyClassNamesFile) {
        this.entityPropertyClassNamesFile = entityPropertyClassNamesFile;
    }

    public File getDestDir() {
        if (destDir == null) {
            return new File(getProject().getBaseDir(), "src");
        }
        return destDir;
    }

    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
