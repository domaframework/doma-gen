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
package org.seasar.doma.extension.gen.internal.util;

import java.io.File;
import java.io.IOException;

import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.internal.message.Message;

/**
 * @author taedium
 * 
 */
public final class FileUtil {

    public static String getCanonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        }
    }

    public static File createJavaFile(File baseDir, String className) {
        AssertionUtil.assertNotNull(baseDir, className);
        String javaFilePath = className.replace('.', File.separatorChar)
                + ".java";
        return new File(baseDir, javaFilePath);
    }

    public static File createSqlDir(File baseDir, String className,
            String fileName) {
        AssertionUtil.assertNotNull(baseDir, className);
        File metaInfDir = new File(baseDir, "META-INF");
        File dir = new File(metaInfDir, className
                .replace('.', File.separatorChar));
        return new File(dir, fileName);
    }
}
