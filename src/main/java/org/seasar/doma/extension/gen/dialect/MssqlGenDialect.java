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
package org.seasar.doma.extension.gen.dialect;

import org.seasar.doma.extension.gen.ClassConstants;

/**
 * SQL Server用の方言です。
 * 
 * @author taedium
 * @since 1.30.0
 */
public class MssqlGenDialect extends Mssql2008GenDialect {

    /**
     * インスタンスを構築します。
     */
    public MssqlGenDialect() {
    }

    @Override
    public String getName() {
        return "mssql";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.Mssql.getQualifiedName();
    }

    @Override
    public boolean supportsSequence() {
        return true;
    }

}
