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

import java.io.File;

/**
 * 生成に関するコンテキストです。
 * 
 * @author taedium
 * 
 */
public class GenerationContext {

    /** データモデル */
    protected final Object model;

    /** 生成するファイル */
    protected final File file;

    /** テンプレート名 */
    protected final String templateName;

    /** エンコーディング */
    protected final String encoding;

    /** 上書きする場合{@code true} */
    protected final boolean overwrite;

    /**
     * インスタンスを構築します。
     * 
     * @param model
     *            データモデル
     * @param file
     *            生成するファイル
     * @param templateName
     *            テンプレート名
     * @param encoding
     *            生成するファイルのエンコーディング
     * @param overwrite
     *            上書きする場合{@code true}、しない場合{@code false}
     */
    public GenerationContext(Object model, File file, String templateName,
            String encoding, boolean overwrite) {
        if (model == null) {
            throw new GenNullPointerException("model");
        }
        if (file == null) {
            throw new GenNullPointerException("file");
        }
        if (templateName == null) {
            throw new GenNullPointerException("templateName");
        }
        if (encoding == null) {
            throw new GenNullPointerException("fileEncoding");
        }
        this.model = model;
        this.file = file;
        this.templateName = templateName;
        this.encoding = encoding;
        this.overwrite = overwrite;
    }

    /**
     * データモデルを返します。
     * 
     * @return データモデル
     */
    public Object getModel() {
        return model;
    }

    /**
     * 生成するファイルを返します。
     * 
     * @return 生成するファイル
     */
    public File getFile() {
        return file;
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
     * エンコーディングを返します。
     * 
     * @return エンコーディング
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * 上書きする場合{@code true} を返します。
     * 
     * @return 上書きする場合{@code true}
     */
    public boolean isOverwrite() {
        return overwrite;
    }

}
