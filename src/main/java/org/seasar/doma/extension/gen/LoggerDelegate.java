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
 * {@link Logger} から処理を委譲されるクラスです。
 * 
 * @author taedium
 * 
 */
public interface LoggerDelegate {

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     */
    void debug(String message);

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void debug(Throwable throwable);

    /**
     * DEBUGレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void debug(String message, Throwable throwable);

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     */
    void info(String message);

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void info(Throwable throwable);

    /**
     * INFOレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void info(String message, Throwable throwable);

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     */
    void error(String message);

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void error(Throwable throwable);

    /**
     * ERRORレベルの情報を記録します。
     * 
     * @param message
     *            メッセージ
     * @param throwable
     *            ログに関連した {@link Throwable}
     */
    void error(String message, Throwable throwable);

}
