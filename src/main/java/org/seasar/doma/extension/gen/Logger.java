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

/**
 * ロガーです。
 * 
 * @author taedium
 * 
 */
public final class Logger {

    /** デリゲート */
    private static volatile LoggerDelegate delegate = new NullLoggerDelegate();

    private Logger() {
    }

    /**
     * デリゲートを設定します。
     * 
     * @param delegate
     *            デリゲート
     */
    public static void setDelegate(LoggerDelegate delegate) {
        Logger.delegate = delegate;
    }

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * 
     */
    public static void debug(String message) {
        delegate.debug(message);
    }

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void debug(Throwable throwable) {
        delegate.debug(throwable);
    }

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void debug(String message, Throwable throwable) {
        delegate.debug(message, throwable);
    }

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     */
    public static void info(String message) {
        delegate.info(message);
    }

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void info(Throwable throwable) {
        delegate.info(throwable);
    }

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void info(String message, Throwable throwable) {
        delegate.info(message, throwable);
    }

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     */
    public static void error(String message) {
        delegate.error(message);
    }

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void error(Throwable throwable) {
        delegate.error(throwable);
    }

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関する {@link Throwable}
     */
    public static void error(String message, Throwable throwable) {
        delegate.error(message, throwable);
    }
}
