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
 * SQLの設定です。
 * 
 * @author taedium
 * 
 */
public class SqlConfig extends DataType {

    /** SQLを生成する場合 {@code true} */
    protected boolean generate = true;

    /** 同名のSQLファイルを上書きする場合 */
    protected boolean overwrite = true;

    /** 生成されるSQLファイルの出力先ディレクトリ */
    protected File destDir = null;

    /** ベースディレクトリ */
    protected File baseDir = null;

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

    public File getDestDir() {
        if (destDir == null) {
            destDir = new File(baseDir != null ? baseDir : getProject()
                    .getBaseDir(), "src/main/resources");
        }
        return destDir;
    }

    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    /**
     * @param baseDir
     *            the baseDir to set
     */
    protected void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

}
