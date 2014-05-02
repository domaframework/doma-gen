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
package org.seasar.doma.extension.gen.internal.message;

import org.seasar.doma.extension.gen.MessageResource;

/**
 * @author taedium
 * 
 */
public enum Message implements MessageResource {

    DOMAGEN0001("パラメータ[{0}]がnullです。"),
    DOMAGEN0002("クラス[{0}]はクラス[{1}]のサブタイプでなければいけません。"),
    DOMAGEN0003("GenerationType.IDENTITYはRDBMS[{0}]でサポートされていません。"),
    DOMAGEN0004("GenerationType.SEQUENCEはRDBMS[{0}]でサポートされていません。"),
    DOMAGEN0005("テーブルが1つも取得できませんでした。JDBCの接続先、方言名、検索パターンが正しいか確認してください。"),
    DOMAGEN0006("SQLの実行に失敗しました(path=[{0}], lineNumber=[{1}], sql=[{2}])。原因は次のものです。{3}"),
    DOMAGEN0007("必須のプロパティ[{0}]が指定されていません。"),
    DOMAGEN0008("プロパティファイル[{0}]のキー[{1}]が不正な正規表現です。詳細は次のものです。{2}"),
    DOMAGEN0009("プロパティファイル[{0}]のキー[{1}]に対する値[{2}]は存在しないクラス名です。詳細は次のものです。{3}"),
    DOMAGEN0010("プロパティ[{0}]に対応するファイル[{1}]が存在しません。"),
    DOMAGEN0011("サポートされていないカラムタイプです（カラムタイプ名={0}、SQLタイプ={1}）。"),
    DOMAGEN0012("プロパティ[{0}]とプロパティ[{1}]のどちらかには値の指定が必須です。"),
    DOMAGEN0013("プロパティ[{0}]が表すクラス[{1}]が見つかりません。 {2}"),
    DOMAGEN0014("プロパティ[{0}]が表すクラス[{1}]はクラス[{2}]のサブタイプでなければいけません。"),
    DOMAGEN0015("プロパティ[{0}]が表すクラス[{1}]のインスタンス化に失敗しました。クラス[{1}]にはpublicのデフォルトコンストラクターが必須です。{2}"),
    DOMAGEN0016("プロパティ[{0}]が表すクラス[{1}]のインスタンス化に失敗しました。クラス[{1}]にはpublicのデフォルトコンストラクターが必須です。{2}"),
    DOMAGEN0017("方言にはクラス[{0}]が使用されます。"),
    DOMAGEN0018("テーブル[{0}]のカラム[{1}]をJavaのクラスにマッピングできません。(カラム型[{2}] : JDBCのSQL型[{3}])。java.lang.Stringを使用します。"),
    DOMAGEN0019("ファイルを作成しました。{0}"),
    DOMAGEN0020("ファイルを上書きしました。{0}"),
    DOMAGEN0021("エンティティクラス[{0}]のプロパティ[{1}]に対応するカラム[{2}]がテーブル[{3}]に定義されていません。"),
    DOMAGEN0022("プロパティ[{0}]からプロパティ[{1}]を推測できません。プロパティ[{1}]を明示的に指定してください。"),

    DOMAGEN5001("JDBCドライバがロードされていない可能性があります。JDBCドライバをロードするには、クラスパスが通されたMETA-INF/services/java.sql.DriverファイルにJDBCドライバのクラスの完全修飾名を記述してください。 ex) oracle.jdbc.driver.OracleDriver"),
    DOMAGEN5002("urlプロパティが設定されていません。"),

    DOMAGEN9001("例外が発生しました。{0}"), ;

    private final String messagePattern;

    private Message(String messagePattern) {
        this.messagePattern = messagePattern;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessagePattern() {
        return messagePattern;
    }

    @Override
    public String getMessage(Object... args) {
        return MessageFormatter.getMessage(this, args);
    }
}
