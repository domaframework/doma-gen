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

import org.seasar.doma.extension.gen.internal.util.ClassUtil;

/**
 * エンティティプロパティ記述です。
 * 
 * @author taedium
 */
public class EntityPropertyDesc {

    /** 名前 */
    protected String name;

    /** プロパティクラス名 */
    protected String propertyClassName;

    /** 識別子の場合{@code true} */
    protected boolean id;

    /** 識別子の生成方法を示す方法、生成しない場合{@code null} */
    protected GenerationType generationType;

    /** 識別子の初期値 */
    protected Long initialValue;

    /** 識別子の割り当てサイズ */
    protected Long allocationSize;

    /** バージョンの場合{@code true} */
    protected boolean version;

    /** カラム名 */
    protected String columnName;

    /** コメント */
    protected String comment;

    /** カラム名を表示する場合 {@code true} */
    protected boolean showColumnName;

    /** 数値を表す場合 {@code true} */
    protected boolean number;

    /**
     * 時刻を表す場合 {@code true}
     * 
     * @since 1.11.0
     */
    protected boolean time;

    /**
     * 日付を表す場合 {@code true}
     * 
     * @since 1.11.0
     */
    protected boolean date;

    /**
     * タイムスタンプを表す場合 {@code true}
     * 
     * @since 1.11.0
     */
    protected boolean timestamp;

    /** エンティティのクラス名 */
    protected String entityClassName;

    /**
     * 名前を設定します。
     * 
     * @param name
     *            名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 名前を返します。
     * 
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * プロパティクラス名を返します。
     * 
     * @return プロパティクラス名
     */
    public String getPropertyClassName() {
        return propertyClassName;
    }

    /**
     * プロパティクラス名を設定します。
     * 
     * @param propertyClassName
     *            プロパティクラス名
     */
    public void setPropertyClassName(String propertyClassName) {
        this.propertyClassName = propertyClassName;
    }

    /**
     * プロパティクラスの単純名を返します。
     * 
     * @return プロパティクラスの単純名
     */
    public String getPropertyClassSimpleName() {
        return ClassUtil.getSimpleName(propertyClassName);
    }

    /**
     * 識別子の場合 {@code true} を設定します。
     * 
     * @param id
     *            識別子の場合 {@code true}
     */
    public void setId(boolean id) {
        this.id = id;
    }

    /**
     * 識別子の場合 {@code true} を返します。
     * 
     * @return 識別子の場合 {@code true}
     */
    public boolean isId() {
        return id;
    }

    /**
     * 識別子の生成方法を示す方法を設定します。
     * 
     * @param generationType
     *            識別子の生成方法を示す方法、生成しない場合 {@code null}
     */
    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    /**
     * 識別子の生成方法を示す方法を返します。
     * 
     * @return 識別子の生成方法を示す方法、生成しない場合 {@code null}
     */
    public GenerationType getGenerationType() {
        return generationType;
    }

    /**
     * 識別子の初期値を設定します。
     * 
     * @param initialValue
     *            識別子の初期値
     */
    public void setInitialValue(Long initialValue) {
        this.initialValue = initialValue;
    }

    /**
     * 識別子の初期値を返します。
     * 
     * @return 識別子の初期値
     */
    public Long getInitialValue() {
        return initialValue;
    }

    /**
     * 識別子の割り当てサイズを設定します。
     * 
     * @param allocationSize
     *            識別子の割り当てサイズ
     */
    public void setAllocationSize(Long allocationSize) {
        this.allocationSize = allocationSize;
    }

    /**
     * 識別子の割り当てサイズを返します。
     * 
     * @return 識別子の割り当てサイズ
     */
    public Long getAllocationSize() {
        return allocationSize;
    }

    /**
     * バージョンの場合 {@code true} を設定します。
     * 
     * @param version
     *            バージョンの場合 {@code true}
     */
    public void setVersion(boolean version) {
        this.version = version;
    }

    /**
     * バージョンの場合 {@code true} を返します。
     * 
     * @return バージョンの場合 {@code true}
     */
    public boolean isVersion() {
        return version;
    }

    /**
     * カラム名を設定します。
     * 
     * @param columnName
     *            カラム名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * カラム名を返します。
     * 
     * @return カラム名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * コメントを設定します。
     * 
     * @param comment
     *            コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * コメントを返します。
     * 
     * @return コメント
     */
    public String getComment() {
        return comment;
    }

    /**
     * カラム名を表示する場合 {@code true} を返します。
     * 
     * @return カラム名を表示する場合 {@code true}
     */
    public boolean isShowColumnName() {
        return showColumnName;
    }

    /**
     * カラム名を表示する場合 {@code true} を設定します。
     * 
     * @param showColumnName
     *            カラム名を表示する場合 {@code true}
     */
    public void setShowColumnName(boolean showColumnName) {
        this.showColumnName = showColumnName;
    }

    /**
     * 数値を表す場合 {@code true}を返します。
     * 
     * @return the number
     */
    public boolean isNumber() {
        return number;
    }

    /**
     * 数値を表す場合 {@code true}を設定します。
     * 
     * @param number
     *            数値を表す場合 {@code true}
     */
    public void setNumber(boolean number) {
        this.number = number;
    }

    /**
     * 時刻を表す場合 {@code true}を返します。
     * 
     * @return 時刻を表す場合 {@code true}
     * @since 1.11.0
     */
    public boolean isTime() {
        return time;
    }

    /**
     * 時刻を表す場合 {@code true}を設定します。
     * 
     * @param time
     *            時刻を表す場合 {@code true}
     * @since 1.11.0
     */
    public void setTime(boolean time) {
        this.time = time;
    }

    /**
     * 日付を表す場合 {@code true}を返します。
     * 
     * @return 日付を表す場合 {@code true}
     * @since 1.11.0
     */
    public boolean isDate() {
        return date;
    }

    /**
     * 日付を表す場合 {@code true}を設定します。
     * 
     * @param date
     *            日付を表す場合 {@code true}
     * @since 1.11.0
     */
    public void setDate(boolean date) {
        this.date = date;
    }

    /**
     * タイムスタンプを表す場合 {@code true}を返します。
     * 
     * @return タイムスタンプを表す場合 {@code true}
     * @since 1.11.0
     */
    public boolean isTimestamp() {
        return timestamp;
    }

    /**
     * タイムスタンプを表す場合 {@code true}を設定します。
     * 
     * @param timestamp
     *            タイムスタンプを表す場合 {@code true}
     * @since 1.11.0
     */
    public void setTimestamp(boolean timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * エンティティのクラス名を返します。
     * 
     * @return エンティティのクラス名
     */
    public String getEntityClassName() {
        return entityClassName;
    }

    /**
     * エンティティのクラス名を設定します。
     * 
     * @param entityClassName
     *            エンティティのクラス名
     */
    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

}
