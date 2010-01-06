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

import java.util.ArrayList;
import java.util.List;

/**
 * SQL記述のファクトリです。
 * 
 * @author taedium
 * 
 */
public class SqlDescFactory {

    /** 識別子による検索メソッドの名前 */
    public final String selectByIdFileName;

    /** 識別子とバージョンによる検索メソッドの名前 */
    public final String selectByIdAndVersionFileName;

    /**
     * インスタンスを構築します。
     */
    public SqlDescFactory() {
        this("selectById.sql", "selectByIdAndVersion.sql");
    }

    /**
     * インスタンスを構築します。
     * 
     * @param selectByIdFileName
     *            識別子による検索メソッドの名前
     * @param selectByIdAndVersionFileName
     *            識別子とバージョンによる検索メソッドの名前
     */
    public SqlDescFactory(String selectByIdFileName,
            String selectByIdAndVersionFileName) {
        if (selectByIdFileName == null) {
            throw new GenNullPointerException("selectByIdFileName");
        }
        if (selectByIdAndVersionFileName == null) {
            throw new GenNullPointerException("selectByIdAndVersionFileName");
        }
        this.selectByIdFileName = selectByIdFileName;
        this.selectByIdAndVersionFileName = selectByIdAndVersionFileName;
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
            results
                    .add(createSqlDesc(entityDesc, selectByIdFileName, "selectById.sql.ftl"));
            if (entityDesc.getVersionEntityPropertyDesc() != null) {
                results
                        .add(createSqlDesc(entityDesc, selectByIdAndVersionFileName, "selectByIdAndVersion.sql.ftl"));
            }
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
        return sqlFileDesc;
    }

}
