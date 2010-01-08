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
import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.internal.message.Message;

/**
 * @author taedium
 * 
 */
public class Dao extends DataType {

    /** DaoのJavaコードを生成する場合 {@code true} */
    protected boolean generate = true;

    protected boolean overwrite = false;

    /** Daoインタフェースのパッケージ名 */
    protected String packageName = "example.dao";

    /** Daoインタフェースのサフィックス */
    protected String suffix = "Dao";

    /** 設定クラス名 */
    protected String configClassName = null;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getConfigClassName() {
        return configClassName;
    }

    public void setConfigClassName(String configClassName) {
        this.configClassName = configClassName;
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

    public void validate() {
        if (configClassName == null) {
            throw new GenException(Message.DOMAGEN0007, "configClassName");
        }
    }
}
