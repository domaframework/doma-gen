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
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author taedium
 * 
 */
public class GeneratorStub extends Generator {

    private StringWriter writer = new StringWriter(300);

    @Override
    protected boolean exists(File file) {
        return false;
    }

    @Override
    protected void mkdirs(File dir) {
    }

    @Override
    protected Writer openWriter(GenerationContext context) {
        return writer;
    }

    protected String getResult() {
        return writer.toString();
    }

    protected void clear() {
        writer = new StringWriter(300);
    }
}
