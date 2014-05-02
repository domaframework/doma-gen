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
import java.util.HashSet;
import java.util.Set;

import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.FileSet;

/**
 * SQLテストケースの設定です。
 * 
 * @author taedium
 * 
 */
public class SqlTestCaseConfig extends DataType {

    /** 生成する場合{@code true} */
    protected boolean generate = true;

    /** ベースディレクトリ */
    protected File baseDir = null;

    /** Javaコードの出力ディレクトリ */
    protected File destDir = null;

    /** Javaファイルのエンコーディング */
    protected String encoding = "UTF-8";

    /** テストの対象のSQLファイル */
    protected final Set<File> sqlFiles = new HashSet<File>();

    /**
     * @return the destDir
     */
    public File getDestDir() {
        if (destDir == null) {
            destDir = new File(baseDir != null ? baseDir : getProject()
                    .getBaseDir(), "src/test/java");
        }
        return destDir;
    }

    /**
     * @param destDir
     *            the destDir to set
     */
    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding
     *            the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @return the generate
     */
    public boolean isGenerate() {
        return generate;
    }

    /**
     * @param generate
     *            the generate to set
     */
    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    /**
     * 
     * @param fileSet
     *            SQLファイルの集合
     */
    public void addConfiguredFileSet(FileSet fileSet) {
        DirectoryScanner scanner = fileSet.getDirectoryScanner(getProject());
        File baseDir = fileSet.getDir();
        for (String fileName : scanner.getIncludedFiles()) {
            sqlFiles.add(new File(baseDir, fileName));
        }
    }

    /**
     * @return the sqlFiles
     */
    public Set<File> getSqlFiles() {
        return sqlFiles;
    }

    /**
     * @param baseDir
     *            the baseDir to set
     */
    protected void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

}
