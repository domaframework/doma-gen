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

import java.io.File;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.seasar.doma.extension.gen.internal.util.ResourceUtil;

/**
 * @author taedium
 * 
 */
public class EntityPropertyClassNameResolverTest extends TestCase {

    public void testResolve2() throws Exception {
        EntityDesc entityDesc = new EntityDesc();
        entityDesc.setPackageName("aaa");
        entityDesc.setSimpleName("Bbb");

        EntityPropertyClassNameResolver resolver = new EntityPropertyClassNameResolver(
                null);
        resolver.patternMap.put(Pattern.compile("aaa\\.Bbb@ccc"), "String");
        assertEquals("String", resolver.resolve(entityDesc, "ccc", "Integer"));
        assertEquals("Integer", resolver.resolve(entityDesc, "ddd", "Integer"));
    }

    public void testResolve() throws Exception {
        String path = getClass().getName().replace('.', '/') + ".properties";
        File file = ResourceUtil.getResourceAsFile(path);
        EntityPropertyClassNameResolver resolver = new EntityPropertyClassNameResolver(
                file);

        EntityDesc entityDesc = new EntityDesc();
        entityDesc.setPackageName("aaa");
        entityDesc.setSimpleName("Bbb");
        assertEquals("java.lang.String", resolver
                .resolve(entityDesc, "ccc", "java.lang.Integer"));
        assertEquals("java.lang.Long", resolver
                .resolve(entityDesc, "ddd", "java.lang.Integer"));

        entityDesc = new EntityDesc();
        entityDesc.setPackageName("xxx");
        entityDesc.setSimpleName("Yyy");
        assertEquals("java.lang.Double", resolver
                .resolve(entityDesc, "ccc", "java.lang.Integer"));
    }
}
