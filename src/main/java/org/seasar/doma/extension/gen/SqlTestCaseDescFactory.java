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
import java.sql.Driver;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.seasar.doma.extension.gen.internal.util.ClassUtil;

/**
 * SQLテストケース記述のファクトリです。
 * 
 * @author taedium
 */
public class SqlTestCaseDescFactory {

    /** {@code org.seasar.doma.jdbc.dialect.Dialect}のサブクラスの名前 */
    protected final String dialectClassName;

    /** {@link Driver} のサブクラスの名前 */
    protected final String driverClassName;

    /** JDBC接続URL */
    protected final String url;

    /** JDBC接続ユーザ */
    protected final String user;

    /** JDBC接続パスワード */
    protected final String password;

    /** テスト対象SQLファイルのセット */
    protected final Set<File> sqlFiles = new TreeSet<File>();

    /** クラス記述のサポートクラス */
    protected final ClassDescSupport classDescSupport = new ClassDescSupport();

    /**
     * インスタンスを構築します。
     * 
     * @param dialectClassName
     *            {@code org.seasar.doma.jdbc.dialect.Dialect}のサブクラスの名前
     * @param driverClassName
     *            {@link Driver} のサブクラスの名前
     * @param url
     *            JDBC接続URL
     * @param user
     *            JDBC接続ユーザ
     * @param password
     *            JDBC接続パスワード
     */
    public SqlTestCaseDescFactory(String dialectClassName,
            String driverClassName, String url, String user, String password) {
        if (dialectClassName == null) {
            throw new GenNullPointerException("dialectClassName");
        }
        if (driverClassName == null) {
            throw new GenNullPointerException("driverClassName");
        }
        if (url == null) {
            throw new GenNullPointerException("url");
        }
        if (user == null) {
            throw new GenNullPointerException("user");
        }
        if (password == null) {
            throw new GenNullPointerException("password");
        }
        if (sqlFiles == null) {
            throw new GenNullPointerException("sqlFiles");
        }
        this.dialectClassName = dialectClassName;
        this.driverClassName = driverClassName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.sqlFiles.addAll(sqlFiles);
    }

    /**
     * SQLテスト記述を作成します。
     * 
     * @param className
     *            クラス名
     * @param methodDescs
     *            メソッド記述のリスト
     * @return SQLテスト記述
     */
    public SqlTestCaseDesc createSqlFileTestDesc(String className,
            List<SqlTestMethodDesc> methodDescs) {
        SqlTestCaseDesc sqlTestCaseDesc = new SqlTestCaseDesc();
        sqlTestCaseDesc.setPackageName(ClassUtil.getPackageName(className));
        sqlTestCaseDesc.setSimpleName(ClassUtil.getSimpleName(className));
        sqlTestCaseDesc.setAbstrct(false);
        sqlTestCaseDesc.setDialectClassName(dialectClassName);
        sqlTestCaseDesc.setDriverClassName(driverClassName);
        sqlTestCaseDesc.setUrl(url);
        sqlTestCaseDesc.setUser(user);
        sqlTestCaseDesc.setPassword(password);
        sqlTestCaseDesc.setTemplateName(Constants.SQL_TEST_CASE_TEMPLATE);
        sqlTestCaseDesc.setMethodDescs(methodDescs);
        return sqlTestCaseDesc;
    }

}
