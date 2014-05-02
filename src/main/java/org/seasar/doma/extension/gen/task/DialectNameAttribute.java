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

import java.util.List;

import org.apache.tools.ant.types.EnumeratedAttribute;
import org.seasar.doma.extension.gen.dialect.GenDialectRegistry;

/**
 * 方言名を表します。
 * <p>
 * 方言に対応するAntの列挙型です。
 * 
 * @author taedium
 * 
 */
public class DialectNameAttribute extends EnumeratedAttribute {

    @Override
    public String[] getValues() {
        List<String> dialectNames = GenDialectRegistry.getDialectNames();
        String[] results = new String[dialectNames.size()];
        int index = 0;
        for (String dialectName : dialectNames) {
            results[index] = dialectName;
            index++;
        }
        return results;
    }

}
