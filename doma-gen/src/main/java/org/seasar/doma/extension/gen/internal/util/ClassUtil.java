/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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
package org.seasar.doma.extension.gen.internal.util;

import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.internal.message.GenMessageCode;

/**
 * @author taedium
 * 
 */
public final class ClassUtil {

    public static <T> T newInstance(Class<T> clazz) {
        AssertionUtil.assertNotNull(clazz);
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new GenException(GenMessageCode.DOMAGEN9001, e, e);
        } catch (IllegalAccessException e) {
            throw new GenException(GenMessageCode.DOMAGEN9001, e, e);
        }
    }

    public static <T> T newInstance(Class<T> supertype, String className) {
        AssertionUtil.assertNotNull(className, supertype);
        Class<?> c = forName(className);
        return supertype.cast(newInstance(c));
    }

    public static Class<?> forName(String className) {
        AssertionUtil.assertNotNull(className);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new GenException(GenMessageCode.DOMAGEN9001, e, e);
        }
    }

    public static String getPackageName(String qualifiedName) {
        AssertionUtil.assertNotNull(qualifiedName);
        int pos = qualifiedName.lastIndexOf('.');
        if (pos < 0) {
            return "";
        }
        return qualifiedName.substring(0, pos);
    }

    public static String getSimpleName(String qualifiedName) {
        AssertionUtil.assertNotNull(qualifiedName);
        int pos = qualifiedName.lastIndexOf('.');
        if (pos < 0) {
            return qualifiedName;
        }
        return qualifiedName.substring(pos + 1);
    }

}
