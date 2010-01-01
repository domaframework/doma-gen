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

import java.io.File;

import junit.framework.TestCase;

/**
 * @author taedium
 * 
 */
public class FileUtilTest extends TestCase {

    public void testCreateJavaFile() throws Exception {
        File file = FileUtil.createJavaFile(new File("."), "aaa.bbb.Ccc");
        assertEquals("Ccc.java", file.getName());
    }
}
