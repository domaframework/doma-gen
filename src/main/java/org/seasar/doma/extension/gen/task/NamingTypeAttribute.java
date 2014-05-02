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

import java.util.EnumSet;

import org.apache.tools.ant.types.EnumeratedAttribute;
import org.seasar.doma.extension.gen.NamingType;

/**
 * ネーミング規約を表します。
 * <p>
 * {@link NamingType} に対応するAntの列挙型です。
 * 
 * @author taedium
 * 
 */
public class NamingTypeAttribute extends EnumeratedAttribute {

    @Override
    public String[] getValues() {
        EnumSet<NamingType> set = EnumSet.allOf(NamingType.class);
        String[] results = new String[set.size()];
        int index = 0;
        for (NamingType namingType : set) {
            results[index] = namingType.name().toLowerCase();
            index++;
        }
        return results;
    }

    /**
     * {@link NamingType} に変換します。
     * 
     * @return 対応する {@link NamingType}
     */
    public NamingType convertToEnum() {
        if (NamingType.LOWER_CASE.name().equalsIgnoreCase(value)) {
            return NamingType.LOWER_CASE;
        }
        if (NamingType.UPPER_CASE.name().equalsIgnoreCase(value)) {
            return NamingType.UPPER_CASE;
        }
        if (NamingType.SNAKE_LOWER_CASE.name().equalsIgnoreCase(value)) {
            return NamingType.SNAKE_LOWER_CASE;
        }
        if (NamingType.SNAKE_UPPER_CASE.name().equalsIgnoreCase(value)) {
            return NamingType.SNAKE_UPPER_CASE;
        }
        if (NamingType.NONE.name().equalsIgnoreCase(value)) {
            return NamingType.NONE;
        }
        throw new AssertionError("unreachable.");
    }
}
