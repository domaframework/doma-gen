package org.seasar.doma.extension.gen.task;

import java.io.File;
import java.sql.Driver;
import java.util.List;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.DaoDesc;
import org.seasar.doma.extension.gen.DaoDescFactory;
import org.seasar.doma.extension.gen.EntityDesc;
import org.seasar.doma.extension.gen.EntityDescFactory;
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
import org.seasar.doma.extension.gen.dialect.Dialect;
import org.seasar.doma.extension.gen.dialect.DialectRegistry;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.AssertionUtil;
import org.seasar.doma.extension.gen.internal.util.FileUtil;

/**
 * エンティティとDaoのJavaコードを生成します。
 * 
 * @author taedium
 * 
 */
public class Gen extends AbstractTask {

    /** 方言名 */
    protected DialectNameAttribute dialectName = null;

    /** {@link Dialect} のサブタイプのクラス名 */
    protected String dialectClassName = null;

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

    /** バージョンカラム名のパターン。このパターンに合致した場合は {@code org.seasar.doma.Versino} が注釈されます。 */
    protected String versionColumnNamePattern = "VERSION([_]?NO)?";

    /** テンプレートのエンコーディング */
    protected String templateEncoding = "UTF-8";

    /** テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null} */
    protected File templatePrimaryDir = null;

    /** 方言 */
    protected Dialect dialect;

    /** データソース */
    protected DataSource dataSource;

    /** テーブルメタデータ */
    protected TableMetaReader tableMetaReader;

    /** エンティティプロパティのクラス名リゾルバ */
    protected EntityPropertyClassNameResolver entityPropertyClassNameResolver;

    /** エンティティ記述のファクトリ */
    protected EntityDescFactory entityDescFactory;

    /** エンティティプロパティ記述のファクトリ */
    protected EntityPropertyDescFactory entityPropertyDescFactory;

    /** Dao記述のファクトリ */
    protected DaoDescFactory daoDescFactory;

    /** SQL記述ファクトリ */
    protected SqlDescFactory sqlDescFactory;

    /** ジェネレータ */
    protected Generator generator;

    protected Entity entity;

    protected Dao dao;

    protected Sql sql;

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
     * {@link Dialect} のサブタイプのクラス名を設定します。
     * 
     * @param dialectClassName
     *            {@link Dialect} のサブタイプのクラス名
     */
    public void setDialectClassName(String dialectClassName) {
        this.dialectClassName = dialectClassName;
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

    public Object createEntity() {
        entity = new Entity();
        return entity;
    }

    public Object createDao() {
        dao = new Dao();
        return dao;
    }

    public Object createSql() {
        sql = new Sql();
        return sql;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
    }

    @Override
    protected void doPrepare() {
        if (dialectClassName != null) {
            dialect = newInstance(Dialect.class, dialectClassName, "dialectClassName");
        } else {
            dialect = DialectRegistry.lookup(dialectName.getValue());
            AssertionUtil.assertNotNull(dialect);
        }
        Logger.info(Message.DOMAGEN0017
                .getMessage(dialect.getClass().getName()));

        Driver driver = newInstance(Driver.class, driverClassName, "driverClassName");
        dataSource = globalFactory
                .createDataSource(driver, user, password, url);
        tableMetaReader = globalFactory
                .createTableMetaReader(dialect, dataSource, schemaName, tableNamePattern, ignoredTableNamePattern);
        entityPropertyClassNameResolver = globalFactory
                .createEntityPropertyClassNameResolver(entity
                        .getEntityPropertyClassNamesFile());
        entityPropertyDescFactory = globalFactory
                .createEntityPropertyDescFactory(dialect, entityPropertyClassNameResolver, versionColumnNamePattern, entity
                        .getGenerationType() == null ? null : entity
                        .getGenerationType().convertToEnum(), entity
                        .getInitialValue(), entity.getAllocationSize(), entity
                        .isShowColumnName());
        entityDescFactory = globalFactory
                .createEntityDescFactory(entity.getPackageName(), entity
                        .getSuperclassName(), entity.getListenerClassName(), entityPropertyDescFactory, entity
                        .getNamingType() == null ? NamingType.NONE : entity
                        .getNamingType().convertToEnum(), entity
                        .isShowCatalogName(), entity.isShowSchemaName(), entity
                        .isShowTableName(), entity.isShowDbComment(), entity
                        .isUseAccessor());
        daoDescFactory = globalFactory.createDaoDescFactory(dao
                .getPackageName(), dao.getSuffix(), dao.getConfigClassName());
        sqlDescFactory = globalFactory.createSqlDescFactory();
        generator = globalFactory
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
            if (entity.isGenerate()) {
                generateEntity(entityDesc);
            }
            DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
            if (dao.isGenerate()) {
                generateDao(daoDesc);
            }
            if (sql.isGenerate()) {
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
        File javaFile = FileUtil.createJavaFile(entity.getDestDir(), entityDesc
                .getQualifiedName());
        GenerationContext context = new GenerationContext(entityDesc, javaFile,
                entityDesc.getTemplateName(), entity.getEncoding(), entity
                        .isOverwrite());
        generator.generate(context);
    }

    /**
     * DaoのJavaコードを生成します。
     * 
     * @param daoDesc
     *            Dao記述
     */
    protected void generateDao(DaoDesc daoDesc) {
        File javaFile = FileUtil.createJavaFile(dao.getDestDir(), daoDesc
                .getQualifiedName());
        GenerationContext context = new GenerationContext(daoDesc, javaFile,
                daoDesc.getTemplateName(), dao.getEncoding(), dao.isOverwrite());
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
        File sqlFile = FileUtil.createSqlDir(sql.getDestDir(), daoDesc
                .getQualifiedName(), sqlDesc.getFileName());
        GenerationContext context = new GenerationContext(sqlDesc, sqlFile,
                sqlDesc.getTemplateName(), "UTF-8", sql.isOverwrite());
        generator.generate(context);
    }

}
