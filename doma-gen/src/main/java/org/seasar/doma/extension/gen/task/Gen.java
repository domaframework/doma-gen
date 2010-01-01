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
import org.seasar.doma.extension.gen.TableMeta;
import org.seasar.doma.extension.gen.TableMetaReader;
import org.seasar.doma.extension.gen.dialect.Dialect;
import org.seasar.doma.extension.gen.internal.message.GenMessageCode;
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

    /** 識別子の生成方法、指定しない場合は {@code null} */
    protected GenerationTypeAttribute generationType = null;

    /** ネーミング規約、指定しない場合は {@code null} */
    protected NamingTypeAttribute namingType = null;

    /** 識別子の初期値、指定しない場合は {@code null} */
    protected Long initialValue = null;

    /** 識別子の割り当てサイズ、指定しない場合は {@code null} */
    protected Long allocationSize = null;

    /** {@code org.seasar.doma.Table#catalog()} でカタログ名を表示する場合 {@code true} */
    protected boolean showCatalogName = false;

    /** {@code org.seasar.doma.Table#schema()} でスキーマ名を表示する場合 {@code true} */
    protected boolean showSchemaName = false;

    /** {@code org.seasar.doma.Table#name()} でテーブル名を表示する場合 {@code true} */
    protected boolean showTableName = true;

    /** {@code org.seasar.doma.Column#name()} でカラム名を表示する場合 {@code true} */
    protected boolean showColumnName = true;

    /** エンティティクラスでアクセッサーを使用する場合 {@code true} */
    protected boolean useAccessor = true;

    /** エンティティクラスのテンプレート名 */
    protected String entityTemplateName = "entity.ftl";

    /** Daoインタフェースのテンプレート名 */
    protected String daoTemplateName = "dao.ftl";

    /** テンプレートのエンコーディング */
    protected String templateEncoding = "UTF-8";

    /** テンプレートを格納するプライマリディレクトリ、使用しない場合 {@code null} */
    protected File templatePrimaryDir = null;

    /** エンティティクラスのパッケージ名 */
    protected String entityPackageName = "example.entity";

    /** Daoインタフェースのパッケージ名 */
    protected String daoPackageName = "example.dao";

    /** Daoインタフェースのサフィックス */
    protected String daoSuffix = "Dao";

    /** 設定クラス名 */
    protected String configClassName = null;

    /** エンティティプロパティ名の正規表現をキー、クラス名を値とするプロパティファイル */
    protected File propertyClassNamesFile = null;

    /** エンティティのJavaコードを生成する場合 {@code true} */
    protected boolean genEntity = true;

    /** DaoのJavaコードを生成する場合 {@code true} */
    protected boolean genDao = true;

    /** すべてのエンティティクラスに共通のスーパークラスの名前、指定しない場合は {@code null} **/
    protected String entitySuperclassName = null;

    /** すべてのエンティティクラスに共通のエンティティリスナーの名前、指定しない場合は {@code null} **/
    protected String entityListenerClassName = null;

    /** 生成されるJavaファイルの出力先ディレクトリ */
    protected File javaDestDir;

    /** Javaファイルのエンコーディング */
    protected String javaEncoding = "UTF-8";

    /** 生成されるJavaファイルと同名のファイルが存在する際に上書きする場合{@code true}、しない場合{@code false} */
    protected boolean overwrite = false;

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

    /** ジェネレータ */
    protected Generator generator;

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
     * @link Dialect} のサブタイプのクラス名を設定します。
     * @param dialectClassName
     * @link Dialect} のサブタイプのクラス名
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
     * 識別子の生成方法を設定します。
     * 
     * @param generationType
     *            識別子の生成方法、指定しない場合は {@code null}
     */
    public void setGenerationType(GenerationTypeAttribute generationType) {
        this.generationType = generationType;
    }

    /**
     * ネーミング規約を設定します。
     * 
     * @param namingType
     *            ネーミング規約、指定しない場合は {@code null}
     */
    public void setNamingType(NamingTypeAttribute namingType) {
        this.namingType = namingType;
    }

    /**
     * 識別子の初期値を設定します。
     * 
     * @param initialValue
     *            識別子の初期値、指定しない場合は {@code null}
     */
    public void setInitialValue(Long initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * 識別子の割り当てサイズを設定します。
     * 
     * @param allocationSize
     *            識別子の割り当てサイズ、指定しない場合は {@code null}
     */
    public void setAllocationSize(Long allocationSize) {
        this.allocationSize = allocationSize;
    }

    /**
     * {@code org.seasar.doma.Table#catalog()} でカタログ名を表示する場合 {@code true}
     * を設定します。
     * 
     * @param showCatalogName
     *            {@code org.seasar.doma.Table#catalog()} でカタログ名を表示する場合 {@code
     *            true}
     */
    public void setShowCatalogName(boolean showCatalogName) {
        this.showCatalogName = showCatalogName;
    }

    /**
     * {@code org.seasar.doma.Table#schema()} でスキーマ名を表示する場合 {@code true} を設定します。
     * 
     * @param showSchemaName
     *            {@code org.seasar.doma.Table#schema()} でスキーマ名を表示する場合 {@code
     *            true}
     */
    public void setShowSchemaName(boolean showSchemaName) {
        this.showSchemaName = showSchemaName;
    }

    /**
     * {@code org.seasar.doma.Table#name()} でテーブル名を表示する場合 {@code true} を設定します。
     * 
     * @param showTableName
     *            {@code org.seasar.doma.Table#name()} でテーブル名を表示する場合 {@code
     *            true}
     */
    public void setShowTableName(boolean showTableName) {
        this.showTableName = showTableName;
    }

    /**
     * {@code org.seasar.doma.Column#name()} でカラム名を表示する場合 {@code true} を設定します。
     * 
     * @param showColumnName
     *            {@code org.seasar.doma.Column#name()} でカラム名を表示する場合 {@code
     *            true}
     */
    public void setShowColumnName(boolean showColumnName) {
        this.showColumnName = showColumnName;
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
     * エンティティクラスのパッケージ名を設定します。
     * 
     * @param entityPackageName
     *            エンティティクラスのパッケージ名
     */
    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    /**
     * すべてのエンティティクラスに共通のスーパークラスの名前を設定します。
     * 
     * @param entitySuperclassName
     *            すべてのエンティティクラスに共通のスーパークラスの名前、指定しない場合は {@code null}
     */
    public void setEntitySuperclassName(String entitySuperclassName) {
        this.entitySuperclassName = entitySuperclassName;
    }

    /**
     * すべてのエンティティクラスに共通のエンティティリスナーの名前を設定します。
     * 
     * @param entityListenerClassName
     *            すべてのエンティティクラスに共通のエンティティリスナーの名前、指定しない場合は {@code null}
     */
    public void setEntityListenerClassName(String entityListenerClassName) {
        this.entityListenerClassName = entityListenerClassName;
    }

    /**
     * 生成されるJavaファイルの出力先ディレクトリを設定します。
     * 
     * @param javaDestDir
     *            生成されるJavaファイルの出力先ディレクトリ
     */
    public void setJavaDestDir(File javaDestDir) {
        this.javaDestDir = javaDestDir;
    }

    /**
     * Javaファイルのエンコーディングを設定します。
     * 
     * @param javaEncoding
     *            Javaファイルのエンコーディング
     */
    public void setJavaEncoding(String javaEncoding) {
        this.javaEncoding = javaEncoding;
    }

    /**
     * 生成されるJavaファイルと同名のファイルが存在する際に上書きする場合{@code true} を設定します。
     * 
     * @param overwrite
     *            生成されるJavaファイルと同名のファイルが存在する際に上書きする場合{@code true}、しない場合{@code
     *            false}
     */
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    /**
     * エンティティクラスでアクセッサーを使用する場合 {@code true} を設定します。
     * 
     * @param useAccessor
     *            エンティティクラスでアクセッサーを使用する場合 {@code true}
     */
    public void setUseAccessor(boolean useAccessor) {
        this.useAccessor = useAccessor;
    }

    /**
     * Daoインタフェースのパッケージ名を設定します。
     * 
     * @param daoPackageName
     *            Daoインタフェースのパッケージ名
     */
    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    /**
     * Daoインタフェースのサフィックスを設定します。
     * 
     * @param daoSuffix
     *            Daoインタフェースのサフィックス
     */
    public void setDaoSuffix(String daoSuffix) {
        this.daoSuffix = daoSuffix;
    }

    /**
     * エンティティのJavaコードを生成する場合 {@code true}を設定します。
     * 
     * @param genEntity
     *            エンティティのJavaコードを生成する場合 {@code true}
     */
    public void setGenEntity(boolean genEntity) {
        this.genEntity = genEntity;
    }

    /**
     * DaoのJavaコードを生成する場合 {@code true} を設定します。
     * 
     * @param genDao
     *            DaoのJavaコードを生成する場合 {@code true}
     */
    public void setGenDao(boolean genDao) {
        this.genDao = genDao;
    }

    /**
     * 設定クラス名を設定します。
     * 
     * @param configClassName
     *            設定クラス名
     */
    public void setConfigClassName(String configClassName) {
        this.configClassName = configClassName;
    }

    /**
     * エンティティプロパティ名の正規表現をキー、クラス名を値とするプロパティファイルを設定します。
     * 
     * @param propertyClassNamesFile
     *            エンティティプロパティ名の正規表現をキー、クラス名を値とするプロパティファイル
     */
    public void setPropertyClassNamesFile(File propertyClassNamesFile) {
        this.propertyClassNamesFile = propertyClassNamesFile;
    }

    @Override
    protected void doValidate() {
        if (javaDestDir == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007, "javaDestDir");
        }
        if (dialectName == null && dialectClassName == null) {
            throw new GenException(GenMessageCode.DOMAGEN0012, "dialectName",
                    "dialectClassName");
        }
        if (driverClassName == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007,
                    "driverClassName");
        }
        if (url == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007, "url");
        }
        if (user == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007, "user");
        }
        if (password == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007, "password");
        }
        if (configClassName == null) {
            throw new GenException(GenMessageCode.DOMAGEN0007,
                    "configClassName");
        }
    }

    @Override
    protected void doPrepare() {
        dialect = globalFactory.createDialect(dialectName == null ? null
                : dialectName.getValue(), dialectClassName);
        dataSource = globalFactory
                .createDataSource(user, password, url, driverClassName);
        tableMetaReader = globalFactory
                .createTableMetaReader(dialect, dataSource, schemaName, tableNamePattern, ignoredTableNamePattern);
        entityPropertyClassNameResolver = globalFactory
                .createEntityPropertyClassNameResolver(null);
        entityPropertyDescFactory = globalFactory
                .createEntityPropertyDescFactory(dialect, entityPropertyClassNameResolver, versionColumnNamePattern, namingType == null ? null
                        : namingType.convertToEnum(), generationType == null ? null
                        : generationType.convertToEnum(), initialValue, allocationSize, showColumnName);
        entityDescFactory = globalFactory
                .createEntityDescFactory(entityPackageName, entitySuperclassName, entityListenerClassName, entityPropertyDescFactory, namingType == null ? null
                        : namingType.convertToEnum(), showCatalogName, showSchemaName, showTableName, useAccessor);
        daoDescFactory = globalFactory
                .createDaoDescFactory(daoPackageName, daoSuffix, configClassName);
        generator = globalFactory
                .createGenerator(templateEncoding, templatePrimaryDir);
    }

    @Override
    protected void doRun() {
        List<TableMeta> tableMetas = tableMetaReader.read();
        for (TableMeta tableMeta : tableMetas) {
            EntityDesc entityDesc = entityDescFactory
                    .createEntityDesc(tableMeta);
            if (genEntity) {
                generateEntity(entityDesc);
            }
            DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
            if (genDao) {
                generateDao(daoDesc);
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
        File javaFile = FileUtil.createJavaFile(javaDestDir, entityDesc
                .getQualifiedName());
        GenerationContext context = new GenerationContext(entityDesc, javaFile,
                entityTemplateName, javaEncoding, overwrite);
        generator.generate(context);
    }

    /**
     * DaoのJavaコードを生成します。
     * 
     * @param daoDesc
     *            Dao記述
     */
    protected void generateDao(DaoDesc daoDesc) {
        File javaFile = FileUtil.createJavaFile(javaDestDir, daoDesc
                .getQualifiedName());
        GenerationContext context = new GenerationContext(daoDesc, javaFile,
                daoTemplateName, javaEncoding, overwrite);
        generator.generate(context);
    }

}
