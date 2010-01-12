package org.seasar.doma.extension.gen.task;

import java.io.File;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.DaoDesc;
import org.seasar.doma.extension.gen.DaoDescFactory;
import org.seasar.doma.extension.gen.EntityDesc;
import org.seasar.doma.extension.gen.EntityDescFactory;
import org.seasar.doma.extension.gen.EntityListenerDesc;
import org.seasar.doma.extension.gen.EntityListenerDescFactory;
import org.seasar.doma.extension.gen.EntityPropertyClassNameResolver;
import org.seasar.doma.extension.gen.EntityPropertyDescFactory;
import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.GenerationContext;
import org.seasar.doma.extension.gen.Generator;
import org.seasar.doma.extension.gen.Logger;
import org.seasar.doma.extension.gen.NamingType;
import org.seasar.doma.extension.gen.SqlDesc;
import org.seasar.doma.extension.gen.SqlDescFactory;
import org.seasar.doma.extension.gen.TableMeta;
import org.seasar.doma.extension.gen.TableMetaReader;
import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.dialect.GenDialectRegistry;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.AssertionUtil;
import org.seasar.doma.extension.gen.internal.util.FileUtil;

/**
 * コードを生成します。
 * <p>
 * 次のコードを生成できます。
 * </p>
 * <ul>
 * <li>エンティティクラス</li>
 * <li>エンティティリスナークラス</li>
 * <li>Daoインタフェース</li>
 * <li>SQL</li>
 * </ul>
 * 
 * @author taedium
 * 
 */
public class Gen extends AbstractTask {

    /** 方言名 */
    protected DialectNameAttribute dialectName = null;

    /** {@link GenDialect} のサブタイプのクラス名 */
    protected String genDialectClassName = null;

    /** {@link Driver} のサブタイプのクラス名 */
    protected String driverClassName = null;

    /** JDBC接続ユーザー */
    protected String user = null;

    /** JDBC接続パスワード */
    protected String password = null;

    /** JDBC接続URL */
    protected String url = null;

    /** 対象とするテーブルが属するスキーマ名 */
    protected String schemaName = null;

    /** 対象とするテーブル名の正規表現 */
    protected String tableNamePattern = ".*";

    /** 対象としないテーブル名の正規表現 */
    protected String ignoredTableNamePattern = ".*\\$.*";

    /** 対象とするテーブルの型のリスト */
    protected List<String> tableTypes = Arrays.asList("TABLE");

    /** バージョンカラム名のパターン。このパターンに合致した場合は {@code org.seasar.doma.Versino} が注釈されます。 */
    protected String versionColumnNamePattern = "VERSION([_]?NO)?";

    /** テンプレートのエンコーディング */
    protected String templateEncoding = "UTF-8";

    /** テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null} */
    protected File templatePrimaryDir = null;

    /** 方言 */
    protected GenDialect dialect;

    /** データソース */
    protected DataSource dataSource;

    /** テーブルメタデータ */
    protected TableMetaReader tableMetaReader;

    /** エンティティプロパティのクラス名リゾルバ */
    protected EntityPropertyClassNameResolver entityPropertyClassNameResolver;

    /** エンティティ記述のファクトリ */
    protected EntityDescFactory entityDescFactory;

    /** エンティティリスナー記述のファクトリ */
    protected EntityListenerDescFactory entityListenerDescFactory;

    /** エンティティプロパティ記述のファクトリ */
    protected EntityPropertyDescFactory entityPropertyDescFactory;

    /** Dao記述のファクトリ */
    protected DaoDescFactory daoDescFactory;

    /** SQL記述ファクトリ */
    protected SqlDescFactory sqlDescFactory;

    /** ジェネレータ */
    protected Generator generator;

    /** エンティティの設定 */
    protected EntityConfig entityConfig;

    /** Daoの設定 */
    protected DaoConfig daoConfig;

    /** SQLの設定 */
    protected SqlConfig sqlConfig;

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
     * {@link GenDialect} のサブタイプのクラス名を設定します。
     * 
     * @param genDialectClassName
     *            {@link GenDialect} のサブタイプのクラス名
     */
    public void setGenDialectClassName(String genDialectClassName) {
        this.genDialectClassName = genDialectClassName;
    }

    /**
     * 対象とするテーブルが属するスキーマ名を設定します。
     * 
     * @param schemaName
     *            対象とするテーブルが属するスキーマ名
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * 対象とするテーブル名の正規表現を設定します。
     * 
     * @param tableNamePattern
     *            対象とするテーブル名の正規表現
     */
    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    /**
     * 対象としないテーブル名の正規表現を設定します。
     * 
     * @param ignoredTableNamePattern
     *            対象としないテーブル名の正規表現
     */
    public void setIgnoredTableNamePattern(String ignoredTableNamePattern) {
        this.ignoredTableNamePattern = ignoredTableNamePattern;
    }

    /**
     * カンマまたは空白で区切られたテーブルの型のリストを設定します
     * 
     * @param tableTypes
     *            カンマまたは空白で区切られたテーブルの型のリスト
     */
    public void setTableTypes(String tableTypes) {
        if (tableTypes != null && tableTypes.length() > 0) {
            StringTokenizer tokenizer = new StringTokenizer(tableTypes,
                    ", \t\n\r\f", false);
            this.tableTypes = new ArrayList<String>();
            while (tokenizer.hasMoreTokens()) {
                String tableType = tokenizer.nextToken();
                this.tableTypes.add(tableType.toUpperCase());
            }
        }
    }

    /**
     * バージョンカラム名のパターンを設定します。
     * 
     * @param versionColumnNamePattern
     *            バージョンカラム名のパターン
     */
    public void setVersionColumnNamePattern(String versionColumnNamePattern) {
        this.versionColumnNamePattern = versionColumnNamePattern;
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
     * エンティティの設定を作成します。
     * 
     * @return エンティティの設定
     */
    public EntityConfig createEntityConfig() {
        entityConfig = new EntityConfig();
        return entityConfig;
    }

    /**
     * Daoの設定を作成します。
     * 
     * @return Daoの設定
     */
    public DaoConfig createDaoConfig() {
        daoConfig = new DaoConfig();
        return daoConfig;
    }

    /**
     * SQLの設定を作成します。
     * 
     * @return SQLの設定
     */
    public SqlConfig createSqlConfig() {
        sqlConfig = new SqlConfig();
        return sqlConfig;
    }

    @Override
    protected void doValidate() {
        if (dialectName == null && genDialectClassName == null) {
            throw new GenException(Message.DOMAGEN0012, "dialectName",
                    "genDialectClassName");
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
        if (entityConfig == null) {
            entityConfig = new EntityConfig();
            entityConfig.setBaseDir(getProject().getBaseDir());
            entityConfig.setGenerate(false);
        }
        if (daoConfig == null) {
            daoConfig = new DaoConfig();
            daoConfig.setBaseDir(getProject().getBaseDir());
            daoConfig.setGenerate(false);
        }
        if (sqlConfig == null) {
            sqlConfig = new SqlConfig();
            sqlConfig.setBaseDir(getProject().getBaseDir());
            sqlConfig.setGenerate(false);
        }
        if (genDialectClassName != null) {
            dialect = newInstance(GenDialect.class, genDialectClassName, "genDialectClassName");
        } else {
            dialect = GenDialectRegistry.lookup(dialectName.getValue());
            AssertionUtil.assertNotNull(dialect);
        }
        Logger.info(Message.DOMAGEN0017
                .getMessage(dialect.getClass().getName()));

        dataSource = createDataSource();
        tableMetaReader = createTableMetaReader();
        entityPropertyClassNameResolver = createEntityPropertyClassNameResolver();
        entityPropertyDescFactory = createEntityPropertyDescFactory();
        entityDescFactory = createEntityDescFactory();
        entityListenerDescFactory = createEntityListenerDescFactory();
        daoDescFactory = createDaoDescFactory();
        sqlDescFactory = createSqlDescFactory();
        generator = createGenerator();
    }

    /**
     * データソースを作成します。
     * 
     * @return データソース
     */
    protected DataSource createDataSource() {
        Driver driver = newInstance(Driver.class, driverClassName, "driverClassName");
        return globalFactory.createDataSource(driver, user, password, url);
    }

    /**
     * テーブルメタデータを作成します。
     * 
     * @return テーブルメタデータ
     */
    protected TableMetaReader createTableMetaReader() {
        return globalFactory
                .createTableMetaReader(dialect, dataSource, schemaName, tableNamePattern, ignoredTableNamePattern, tableTypes);
    }

    /**
     * エンティティプロパティのクラス名リゾルバを作成します。
     * 
     * @return エンティティプロパティのクラス名リゾルバ
     */
    protected EntityPropertyClassNameResolver createEntityPropertyClassNameResolver() {
        return globalFactory.createEntityPropertyClassNameResolver(entityConfig
                .getEntityPropertyClassNamesFile());
    }

    /**
     * グローバルファクトリを作成します。
     * 
     * @return グローバルファクトリ
     */
    protected EntityPropertyDescFactory createEntityPropertyDescFactory() {
        return globalFactory
                .createEntityPropertyDescFactory(dialect, entityPropertyClassNameResolver, versionColumnNamePattern, entityConfig
                        .getGenerationType() == null ? null : entityConfig
                        .getGenerationType().convertToEnum(), entityConfig
                        .getInitialValue(), entityConfig.getAllocationSize(), entityConfig
                        .isShowColumnName());
    }

    /**
     * エンティティ記述ファクトリを作成します。
     * 
     * @return エンティティ記述ファクトリ
     */
    protected EntityDescFactory createEntityDescFactory() {
        return globalFactory
                .createEntityDescFactory(entityConfig.getPackageName(), entityConfig
                        .getSuperclassName(), entityPropertyDescFactory, entityConfig
                        .getNamingType() == null ? NamingType.NONE
                        : entityConfig.getNamingType().convertToEnum(), entityConfig
                        .getOriginalStatesPropertyName(), entityConfig
                        .isShowCatalogName(), entityConfig.isShowSchemaName(), entityConfig
                        .isShowTableName(), entityConfig.isShowDbComment(), entityConfig
                        .isUseAccessor(), entityConfig.isUseListener());
    }

    /**
     * エンティティリスナー記述ファクトリを作成します。
     * 
     * @return エンティティリスナー記述ファクトリ
     */
    protected EntityListenerDescFactory createEntityListenerDescFactory() {
        return globalFactory.createEntityListenerDescFactory(entityConfig
                .getPackageName(), entityConfig.getListenerSuperclassName());
    }

    /**
     * Dao記述ファクトリを作成します。
     * 
     * @return Dao記述ファクトリ
     */
    protected DaoDescFactory createDaoDescFactory() {
        return globalFactory
                .createDaoDescFactory(daoConfig.getPackageName(), daoConfig
                        .getSuffix(), daoConfig.getConfigClassName());
    }

    /**
     * SQL記述ファクトリを作成します。
     * 
     * @return SQL記述ファクトリ
     */
    protected SqlDescFactory createSqlDescFactory() {
        return globalFactory.createSqlDescFactory();
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
        List<TableMeta> tableMetas = tableMetaReader.read();
        if (tableMetas.isEmpty()) {
            throw new GenException(Message.DOMAGEN0005);
        }
        for (TableMeta tableMeta : tableMetas) {
            EntityDesc entityDesc = entityDescFactory
                    .createEntityDesc(tableMeta);
            if (entityConfig.isGenerate()) {
                generateEntity(entityDesc);
                if (entityConfig.isUseListener()) {
                    EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                            .createEntityListenerDesc(entityDesc);
                    generateEntityListener(entityListenerDesc);
                }
            }
            DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
            if (daoConfig.isGenerate()) {
                generateDao(daoDesc);
            }
            if (sqlConfig.isGenerate()) {
                for (SqlDesc sqlDesc : sqlDescFactory
                        .createSqlDescs(entityDesc)) {
                    generateSql(daoDesc, sqlDesc);
                }
            }
        }
    }

    /**
     * エンティティのJavaコードを生成します。
     * 
     * @param entityDesc
     *            エンティティ記述
     */
    protected void generateEntity(EntityDesc entityDesc) {
        File javaFile = FileUtil
                .createJavaFile(entityConfig.getDestDir(), entityDesc
                        .getQualifiedName());
        GenerationContext context = new GenerationContext(entityDesc, javaFile,
                entityDesc.getTemplateName(), entityConfig.getEncoding(),
                entityConfig.isOverwrite());
        generator.generate(context);
    }

    protected void generateEntityListener(EntityListenerDesc entityListenerDesc) {
        File javaFile = FileUtil
                .createJavaFile(entityConfig.getDestDir(), entityListenerDesc
                        .getQualifiedName());
        GenerationContext context = new GenerationContext(entityListenerDesc,
                javaFile, entityListenerDesc.getTemplateName(), entityConfig
                        .getEncoding(), entityConfig.isOverwriteListener());
        generator.generate(context);
    }

    /**
     * DaoのJavaコードを生成します。
     * 
     * @param daoDesc
     *            Dao記述
     */
    protected void generateDao(DaoDesc daoDesc) {
        File javaFile = FileUtil.createJavaFile(daoConfig.getDestDir(), daoDesc
                .getQualifiedName());
        GenerationContext context = new GenerationContext(daoDesc, javaFile,
                daoDesc.getTemplateName(), daoConfig.getEncoding(), daoConfig
                        .isOverwrite());
        generator.generate(context);
    }

    /**
     * SQLを生成します。
     * 
     * @param daoDesc
     *            Dao記述
     * @param sqlDesc
     *            SQL記述
     */
    protected void generateSql(DaoDesc daoDesc, SqlDesc sqlDesc) {
        File sqlFile = FileUtil.createSqlDir(sqlConfig.getDestDir(), daoDesc
                .getQualifiedName(), sqlDesc.getFileName());
        GenerationContext context = new GenerationContext(sqlDesc, sqlFile,
                sqlDesc.getTemplateName(), "UTF-8", sqlConfig.isOverwrite());
        generator.generate(context);
    }

}
