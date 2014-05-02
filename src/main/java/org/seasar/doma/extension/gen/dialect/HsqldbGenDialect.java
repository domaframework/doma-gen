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
 * HSQLDB用の方言です。
 * 
 * @author taedium
 * 
 */
public class HsqldbGenDialect extends StandardGenDialect {

    /**
     * インスタンスを構築します。
     */
    public HsqldbGenDialect() {
        classNameMap.put("int", Integer.class.getName());
        classNameMap.put("varchar_ignorecase", String.class.getName());
    }

    @Override
    public String getName() {
        return "hsqldb";
    }

    @Override
    public String getDialectClassName() {
        return ClassConstants.HsqldbDialect.getQualifiedName();
    }

    @Override
    public String getDefaultSchemaName(String userName) {
        return null;
    }

    @Override
    public boolean isJdbcCommentUnavailable() {
        return true;
    }

    @Override
    public boolean supportsIdentity() {
        return true;
    }

    @Override
    public boolean supportsSequence() {
        return true;
    }
}
