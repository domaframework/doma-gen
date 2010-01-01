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

import java.io.File;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.dialect.Dialect;
import org.seasar.doma.extension.gen.dialect.DialectRegistry;
import org.seasar.doma.extension.gen.internal.util.ClassUtil;

/**
 * グローバルなファクトリです。
 * 
 * @author taedium
 * 
 */
public class GlobalFactory {

    /**
     * 方言を作成します。
     * 
     * @param dialectClassName
     *            方言クラス名
     * @return 方言
     */
    public Dialect createDialect(String dialectName, String dialectClassName) {
        if (dialectClassName != null) {
            return ClassUtil.newInstance(Dialect.class, dialectClassName);
        }
        return DialectRegistry.lookup(dialectName);
    }

    /**
     * データソースを作成します。
     * 
     * @param user
     *            ユーザー
     * @param password
     *            パスワード
     * @param url
     *            接続URL
     * @param driverClassName
     *            JDBCドライバークラス名
     * @return データソース
     */
    public DataSource createDataSource(String user, String password,
            String url, String driverClassName) {
        ClassUtil.forName(driverClassName);
        SimpleDataSource dataSource = new SimpleDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }

    /**
     * データソースメタデータのファクトリを作成します。
     * 
     * @param dialect
     *            方言
     * @param dataSource
     *            データソース
     * @param schemaName
     *            スキーマ名
     * @param tableNamePattern
     *            読み取り対象とするテーブル名のパターン
     * @param ignoredTableNamePattern
     *            読み取り非対象とするテーブル名のパターン
     * @return データソースメタデータのファクトリ
     */
    public TableMetaReader createTableMetaReader(Dialect dialect,
            DataSource dataSource, String schemaName, String tableNamePattern,
            String ignoredTableNamePattern) {
        return new TableMetaReader(dialect, dataSource, schemaName,
                tableNamePattern, ignoredTableNamePattern);
    }

    /**
     * エンティティプロパティ記述のファクトリを作成します。
     * 
     * @param dialect
     *            方言
     * @param propertyClassNameResolver
     *            プロパティクラス名のリゾルバ
     * @param versionColumnNamePattern
     *            バージョンカラム名パターン
     * @param namingType
     *            ネーミング規約
     * @param generationType
     *            識別子を生成する方法
     * @param initialValue
     *            識別子の初期値
     * @param allocationSize
     *            識別子の割り当てサイズ
     * @param showColumnName
     *            カラム名を表示する場合 {@code true}
     * @return エンティティプロパティ記述のファクトリ
     */
    public EntityPropertyDescFactory createEntityPropertyDescFactory(
            Dialect dialect,
            EntityPropertyClassNameResolver propertyClassNameResolver,
            String versionColumnNamePattern, NamingType namingType,
            GenerationType generationType, Long initialValue,
            Long allocationSize, boolean showColumnName) {
        return new EntityPropertyDescFactory(dialect,
                propertyClassNameResolver, versionColumnNamePattern,
                namingType, generationType, initialValue, allocationSize,
                showColumnName);
    }

    /**
     * エンティティ記述のファクトリを作成します。
     * 
     * @param entityPackageName
     *            パッケージ名
     * @param entitySuperclassName
     *            スーパークラス名
     * @param entityListenerClassName
     *            リスナークラス名
     * @param entityPropertyDescFactory
     *            エンティティプロパティ記述のファクトリ
     * @param namingType
     *            ネーミング規約
     * @param showCatalogName
     *            カタログ名を表示する場合 {@code true}
     * @param showSchemaName
     *            スキーマ名を表示する場合 {@code true}
     * @param showTableName
     *            テーブル名を表示する場合 {@code true}
     * @param useAccessor
     *            アクセッサーを使用する場合 {@code true}
     * @return エンティティ記述のファクトリ
     */
    public EntityDescFactory createEntityDescFactory(String entityPackageName,
            String entitySuperclassName, String entityListenerClassName,
            EntityPropertyDescFactory entityPropertyDescFactory,
            NamingType namingType, boolean showCatalogName,
            boolean showSchemaName, boolean showTableName, boolean useAccessor) {
        return new EntityDescFactory(entityPackageName, entitySuperclassName,
                entityListenerClassName, entityPropertyDescFactory, namingType,
                showCatalogName, showSchemaName, showTableName, useAccessor);
    }

    /**
     * Dao記述のファクトリを作成します。
     * 
     * @param daoPackageName
     *            パッケージ名
     * @param daoSuffix
     *            サフィックス
     * @param configClassName
     *            設定クラス名
     * @return Dao記述のファクトリ
     */
    public DaoDescFactory createDaoDescFactory(String daoPackageName,
            String daoSuffix, String configClassName) {
        return new DaoDescFactory(daoPackageName, daoSuffix, configClassName);
    }

    /**
     * エンティティプロパティのクラス名リゾルバを作成します。
     * 
     * @param propertyFile
     *            プロパティファイル
     * @return ドメインクラス名のリゾルバ
     */
    public EntityPropertyClassNameResolver createEntityPropertyClassNameResolver(
            File propertyFile) {
        return new EntityPropertyClassNameResolver(propertyFile);
    }

    /**
     * ジェネレータを作成します。
     * 
     * @param templateEncoding
     *            テンプレートファイルのエンコーディング
     * @param templatePrimaryDir
     *            テンプレートファイルを格納したプライマリディレクトリ、プライマリディレクトリを使用しない場合{@code null}
     * @return ジェネレータ
     */
    public Generator createGenerator(String templateEncoding,
            File templatePrimaryDir) {
        return new Generator(templateEncoding, templatePrimaryDir);
    }
}
