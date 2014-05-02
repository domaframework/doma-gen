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
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.seasar.doma.extension.gen.dialect.GenDialect;

/**
 * SQL記述のファクトリです。
 * 
 * @author taedium
 * 
 */
public class SqlDescFactory {

    /** 識別子による検索メソッドの名前 */
    private final String selectByIdFileName;

    /** 識別子とバージョンによる検索メソッドの名前 */
    private final String selectByIdAndVersionFileName;

    /** テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null} */
    private File templatePrimaryDir;

    /** 方言 */
    private GenDialect dialect;

    /**
     * インスタンスを構築します。
     * 
     * @param templatePrimaryDir
     *            テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null}
     * @param dialect
     *            方言
     * @since 1.11.0
     */
    public SqlDescFactory(File templatePrimaryDir, GenDialect dialect) {
        this("selectById.sql", "selectByIdAndVersion.sql", templatePrimaryDir,
                dialect);
    }

    /**
     * インスタンスを構築します。
     * 
     * @param selectByIdFileName
     *            識別子による検索メソッドの名前
     * @param selectByIdAndVersionFileName
     *            識別子とバージョンによる検索メソッドの名前
     * @param templatePrimaryDir
     *            テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null}
     * @param dialect
     *            方言
     * @since 1.11.0
     */
    public SqlDescFactory(String selectByIdFileName,
            String selectByIdAndVersionFileName, File templatePrimaryDir,
            GenDialect dialect) {
        if (selectByIdFileName == null) {
            throw new GenNullPointerException("selectByIdFileName");
        }
        if (selectByIdAndVersionFileName == null) {
            throw new GenNullPointerException("selectByIdAndVersionFileName");
        }
        if (dialect == null) {
            throw new GenNullPointerException("dialect");
        }
        this.selectByIdFileName = selectByIdFileName;
        this.selectByIdAndVersionFileName = selectByIdAndVersionFileName;
        this.templatePrimaryDir = templatePrimaryDir;
        this.dialect = dialect;
    }

    /**
     * SQL記述のリストを返します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @return SQL記述のリスト
     */
    public List<SqlDesc> createSqlDescs(EntityDesc entityDesc) {
        List<SqlDesc> results = new ArrayList<SqlDesc>();
        if (entityDesc.getIdEntityPropertyDescs().size() > 0) {
            results.add(createSqlDesc(entityDesc, selectByIdFileName, Constants.SELECT_BY_ID_SQL_TEMPLATE));
            if (entityDesc.getVersionEntityPropertyDesc() != null) {
                results.add(createSqlDesc(entityDesc, selectByIdAndVersionFileName, Constants.SELECT_BY_ID_AND_VERSION_SQL_TEMPLATE));
            }
        }
        for (String templateName : findTemplateNames()) {
            String fileName = removeTemplateExtension(templateName);
            results.add(createSqlDesc(entityDesc, fileName, templateName));
        }
        return results;
    }

    /**
     * SQL記述を返します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param fileName
     *            ファイル名
     * @param templateName
     *            テンプレート名
     * @return SQL記述
     */
    protected SqlDesc createSqlDesc(EntityDesc entityDesc, String fileName,
            String templateName) {
        SqlDesc sqlFileDesc = new SqlDesc();
        sqlFileDesc.setFileName(fileName);
        sqlFileDesc.setTemplateName(templateName);
        sqlFileDesc.setEntityDesc(entityDesc);
        sqlFileDesc.setDialect(dialect);
        return sqlFileDesc;
    }

    /**
     * テンプレートのファイル名を探します。
     * 
     * @return テンプレートのファイル名のセット
     * @since 1.7.0
     */
    protected Set<String> findTemplateNames() {
        final Set<String> results = new HashSet<String>();
        if (templatePrimaryDir == null) {
            return results;
        }
        templatePrimaryDir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    String name = file.getName();
                    if (!name.equals(Constants.SELECT_BY_ID_SQL_TEMPLATE)
                            && !name.equals(Constants.SELECT_BY_ID_AND_VERSION_SQL_TEMPLATE)) {
                        if (name.endsWith(Constants.SQL_TEMPLATE_EXTENSION)) {
                            results.add(name);
                        }
                    }
                }
                return false;
            }
        });
        return results;
    }

    /**
     * テンプレートファイルの拡張子を削除します。
     * 
     * @param templateName
     *            テンプレートファイル名
     * @return テンプレートファイルの拡張子を削除した文字列
     * @since 1.7.0
     */
    protected String removeTemplateExtension(String templateName) {
        return templateName.substring(0, templateName.length()
                - Constants.TEMPLATE_EXTENSION.length());
    }

}
