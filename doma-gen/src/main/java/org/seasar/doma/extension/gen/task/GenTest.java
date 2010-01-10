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
import java.sql.Driver;

import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.GenerationContext;
import org.seasar.doma.extension.gen.Generator;
import org.seasar.doma.extension.gen.SqlTestDesc;
import org.seasar.doma.extension.gen.SqlTestDescFactory;
import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.dialect.GenDialectRegistry;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.FileUtil;

/**
 * テスト用のコードを生成します。
 * <p>
 * 次のコードを生成できます。
 * </p>
 * <ul>
 * <li>SQLのテストクラス</li>
 * </ul>
 * 
 * @author taedium
 * 
 */
public class GenTest extends AbstractTask {

    /** 方言名 */
    protected DialectNameAttribute dialectName = null;

    /** {@code org.seasar.doma.jdbc.dialect.Dialect} のサブタイプのクラス名 */
    protected String dialectClassName = null;

    /** {@link Driver} のサブタイプのクラス名 */
    protected String driverClassName = null;

    /** JDBC接続ユーザー */
    protected String user = null;

    /** JDBC接続パスワード */
    protected String password = null;

    /** JDBC接続URL */
    protected String url = null;

    /** テンプレートのエンコーディング */
    protected String templateEncoding = "UTF-8";

    /** テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null} */
    protected File templatePrimaryDir = null;

    /** SQLテスト記述 */
    protected SqlTestConfig sqlTestConfig = null;

    /** SQLテスト記述ファクトリ */
    protected SqlTestDescFactory sqlTestDescFactory = null;

    /** ジェネレータ */
    protected Generator generator = null;

    /**
     * JDBC接続ユーザーを設定します。
     * 
     * @param user
     *            JDBC接続ユーザー
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * JDBC接続パスワードを設定します。
     * 
     * @param password
     *            JDBC接続パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * JDBC接続URLを設定します。
     * 
     * @param url
     *            JDBC接続URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * {@link Driver} のサブタイプのクラス名を設定します。
     * 
     * @param driverClassName
     *            {@link Driver} のサブタイプのクラス名
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * 方言名を設定します。
     * 
     * @param dialectName
     *            方言名
     */
    public void setDialectName(DialectNameAttribute dialectName) {
        this.dialectName = dialectName;
    }

    /**
     * {@code org.seasar.doma.jdbc.dialect.Dialect} のサブタイプのクラス名を設定します。
     * 
     * @param genDialectClassName
     *            {@code org.seasar.doma.jdbc.dialect.Dialect} のサブタイプのクラス名
     */
    public void setDialectClassName(String dialectClassName) {
        this.dialectClassName = dialectClassName;
    }

    /**
     * テンプレートのエンコーディングを設定します。
     * 
     * @param templateEncoding
     *            テンプレートのエンコーディング
     */
    public void setTemplateEncoding(String templateEncoding) {
        this.templateEncoding = templateEncoding;
    }

    /**
     * テンプレートを格納するプライマリディレクトリを設定します。
     * 
     * @param templatePrimaryDir
     *            テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null}
     */
    public void setTemplatePrimaryDir(File templatePrimaryDir) {
        this.templatePrimaryDir = templatePrimaryDir;
    }

    /**
     * SQLテストの設定を作成します。
     * 
     * @return SQLテストの設定
     */
    public SqlTestConfig createSqlTestConfig() {
        sqlTestConfig = new SqlTestConfig();
        return sqlTestConfig;
    }

    @Override
    protected void doValidate() {
        if (dialectName == null && dialectClassName == null) {
            throw new GenException(Message.DOMAGEN0012, "dialectName",
                    "dialectClassName");
        }
        if (driverClassName == null) {
            throw new GenException(Message.DOMAGEN0007, "driverClassName");
        }
        if (url == null) {
            throw new GenException(Message.DOMAGEN0007, "url");
        }
        if (user == null) {
            throw new GenException(Message.DOMAGEN0007, "user");
        }
        if (password == null) {
            throw new GenException(Message.DOMAGEN0007, "password");
        }
    }

    @Override
    protected void doPrepare() {
        if (sqlTestConfig == null) {
            sqlTestConfig = new SqlTestConfig();
            sqlTestConfig.setBaseDir(getProject().getBaseDir());
            sqlTestConfig.setGenerate(false);
        }
        if (dialectClassName == null) {
            GenDialect dialect = GenDialectRegistry.lookup(dialectName
                    .getValue());
            dialectClassName = dialect.getDialectClassConstant()
                    .getQualifiedName();
        }
        sqlTestDescFactory = createSqlTestDescFactory();
        generator = createGenerator();
    }

    /**
     * SQLテスト記述ファクトリを作成します。
     * 
     * @return SQLテスト記述ファクトリ
     */
    protected SqlTestDescFactory createSqlTestDescFactory() {
        return globalFactory
                .createSqlTestDescFactory(sqlTestConfig.getSqlTestClassName(), sqlTestConfig
                        .isAbstrct(), dialectClassName, driverClassName, url, user, password, sqlTestConfig
                        .getSqlFiles());
    }

    /**
     * ジェネレータを作成します。
     * 
     * @return ジェネレータ
     */
    protected Generator createGenerator() {
        return globalFactory
                .createGenerator(templateEncoding, templatePrimaryDir);
    }

    @Override
    protected void doRun() {
        SqlTestDesc sqlTestDesc = sqlTestDescFactory.createSqlFileTestDesc();
        if (sqlTestConfig.isGenerate()) {
            generateSqlTest(sqlTestDesc);
        }
    }

    /**
     * SQLのテストコードを生成します。
     * 
     * @param sqlTestDesc
     *            SQLテスト記述
     */
    protected void generateSqlTest(SqlTestDesc sqlTestDesc) {
        File javaFile = FileUtil
                .createJavaFile(sqlTestConfig.getDestDir(), sqlTestDesc
                        .getQualifiedName());
        GenerationContext context = new GenerationContext(sqlTestDesc,
                javaFile, sqlTestDesc.getTemplateName(), sqlTestConfig
                        .getEncoding(), true);
        generator.generate(context);
    }

}
