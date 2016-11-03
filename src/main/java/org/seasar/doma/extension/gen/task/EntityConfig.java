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
package org.seasar.doma.extension.gen.task;

import java.io.File;

import org.apache.tools.ant.types.DataType;

/**
 * エンティティの設定です。
 *
 * @author taedium
 *
 */
public class EntityConfig extends DataType {

    /** 生成する場合{@code true} */
    protected boolean generate = true;

    /** 同名のエンティティクラスのJavaファイルを上書きする場合{@code true}、しない場合{@code false} */
    protected boolean overwrite = true;

    /** 同名のエンティティリスナークラスのJavaファイルを上書きする場合{@code true}、しない場合{@code false} */
    protected boolean overwriteListener = false;

    /** エンティティクラスに共通のスーパークラスの名前、指定しない場合は {@code null} **/
    protected String superclassName = null;

    /** エンティティリスナーに共通のスーパークラスの名前、指定しない場合は {@code null} **/
    protected String listenerSuperclassName = null;

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

    /** エンティティリスナーを使用する場合 {@code true} */
    protected boolean useListener = true;

    /** {@code org.seasar.doma.OriginalStates} を注釈する対象のプロパティ名 */
    protected String originalStatesPropertyName = null;

    /** エンティティプロパティ名の正規表現をキー、クラス名を値とするプロパティファイル */
    protected File entityPropertyClassNamesFile = null;

    /** 生成されるJavaファイルの出力先ディレクトリ */
    protected File destDir = null;

    /** Javaファイルのエンコーディング */
    protected String encoding = "UTF-8";

    /** ベースディレクトリ */
    protected File baseDir = null;

    protected String sql = null;

    protected String entityPrefix;

    /** エンティティクラスのサフィックス */
    protected String entitySuffix;

    protected String entityName = "Example";

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

    public boolean isOverwriteListener() {
        return overwriteListener;
    }

    public void setOverwriteListener(boolean overwriteListener) {
        this.overwriteListener = overwriteListener;
    }

    public String getSuperclassName() {
        return superclassName;
    }

    public void setSuperclassName(String superclassName) {
        this.superclassName = superclassName;
    }

    public String getListenerSuperclassName() {
        return listenerSuperclassName;
    }

    public void setListenerSuperclassName(String listenerSuperclassName) {
        this.listenerSuperclassName = listenerSuperclassName;
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

    public boolean isUseListener() {
        return useListener;
    }

    public void setUseListener(boolean useListener) {
        this.useListener = useListener;
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
            destDir = new File(baseDir != null ? baseDir : getProject()
                    .getBaseDir(), "src/main/java");
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

    public String getOriginalStatesPropertyName() {
        return originalStatesPropertyName;
    }

    public void setOriginalStatesPropertyName(String originalStatesPropertyName) {
        this.originalStatesPropertyName = originalStatesPropertyName;
    }

    protected void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getEntityPrefix() {
        return entityPrefix;
    }

    public void setEntityPrefix(String entityPrefix) {
        this.entityPrefix = entityPrefix;
    }

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public void setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
