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
package org.seasar.doma.extension.gen.task;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.GlobalFactory;
import org.seasar.doma.extension.gen.Logger;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.AssertionUtil;

/**
 * {@link Task} の抽象クラスです。
 * 
 * @author taedium
 * 
 */
public abstract class AbstractTask extends Task {

    /** グローバルファクトリのクラス名 */
    protected String globalFactoryClassName = GlobalFactory.class.getName();

    /** グローバルファクトリ */
    protected GlobalFactory globalFactory;

    /**
     * グローバルファクトリのクラス名を設定します。
     * 
     * @param globalFactoryClassName
     *            グローバルファクトリのクラス名
     */
    public void setGlobalFactoryClassName(String globalFactoryClassName) {
        this.globalFactoryClassName = globalFactoryClassName;
    }

    /**
     * インスタンスを構築します。
     */
    protected AbstractTask() {
    }

    @Override
    public void execute() throws BuildException {
        Logger.setDelegate(new TaskLoggerDelegate(this));
        Logger.debug("start task");
        try {
            validate();
            prepare();
            run();
        } catch (Throwable t) {
            StringWriter writer = new StringWriter();
            t.printStackTrace(new PrintWriter(writer));
            throw new BuildException(t.getMessage() + writer);
        }
    }

    /**
     * 検証します。
     */
    protected final void validate() {
        doValidate();
    }

    /**
     * サブクラスで検証します。
     */
    protected abstract void doValidate();

    /**
     * 準備します。
     */
    protected final void prepare() {
        globalFactory = newInstance(GlobalFactory.class, globalFactoryClassName, "globalFactoryClassName");
        doPrepare();
    }

    protected <T> T newInstance(Class<T> supertype, String className,
            String propertyName) {
        AssertionUtil.assertNotNull(supertype, className, propertyName);
        Class<?> clazz = forName(className, propertyName);
        if (!supertype.isAssignableFrom(clazz)) {
            throw new GenException(Message.DOMAGEN0014, propertyName,
                    className, supertype.getName());
        }
        try {
            return supertype.cast(clazz.newInstance());
        } catch (InstantiationException e) {
            throw new GenException(Message.DOMAGEN0015, propertyName,
                    className, e);
        } catch (IllegalAccessException e) {
            throw new GenException(Message.DOMAGEN0015, propertyName,
                    className, e);
        }
    }

    protected Class<?> forName(String className, String propertyName) {
        AssertionUtil.assertNotNull(className, propertyName);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new GenException(Message.DOMAGEN0013, propertyName,
                    className, e);
        }
    }

    /**
     * サブクラスで準備します。
     */
    protected abstract void doPrepare();

    /**
     * 実行します。
     */
    protected final void run() {
        doRun();
    }

    /**
     * サブクラスで実行します。
     */
    protected abstract void doRun();
}
