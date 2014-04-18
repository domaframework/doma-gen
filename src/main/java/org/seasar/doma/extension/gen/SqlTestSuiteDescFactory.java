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

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.util.Objects;
import java.util.Set;

import org.seasar.doma.extension.gen.internal.util.FileUtil;
import org.seasar.doma.extension.gen.internal.util.Pair;
import org.seasar.doma.extension.gen.internal.util.StringUtil;

/**
 * @author nakamura-to
 *
 */
public class SqlTestSuiteDescFactory {

    protected SqlTestCaseDescFactory sqlTestCaseDescFactory;

    public SqlTestSuiteDescFactory(SqlTestCaseDescFactory sqlTestCaseDescFactory) {
        if (sqlTestCaseDescFactory == null) {
            throw new GenNullPointerException("sqlTestCaseDescFactory");
        }
        this.sqlTestCaseDescFactory = sqlTestCaseDescFactory;
    }

    public SqlTestSuiteDesc createSqlTestSuiteDesc(Set<File> sqlFileSet) {
        if (sqlFileSet == null) {
            throw new GenNullPointerException("sqlFileSet");
        }
        SqlTestSuiteDesc suiteDesc = new SqlTestSuiteDesc();
        sqlFileSet
                .stream()
                .filter(Objects::nonNull)
                .filter(f -> f.isFile())
                .filter(f -> f.getName().endsWith(".sql"))
                .filter(f -> !f.getName().contains("-"))
                .map(FileUtil::getCanonicalPath)
                .map(path -> path.replace(File.separator, "/"))
                .map(this::fromPathToPair)
                .filter(Objects::nonNull)
                .collect(
                        groupingBy(Pair::getFirst,
                                mapping(Pair::getSecond, toList())))
                .forEach(
                        (className, methodDescs) -> {
                            SqlTestCaseDesc testCaseDesc = sqlTestCaseDescFactory
                                    .createSqlFileTestDesc(className,
                                            methodDescs);
                            suiteDesc.addTestCaseDesc(testCaseDesc);
                        });
        return suiteDesc;
    }

    protected Pair<String, SqlTestMethodDesc> fromPathToPair(String path) {
        int pos = path.indexOf("/META-INF/");
        int pos2 = path.lastIndexOf('/');
        if (pos == -1 || pos2 == -1 || pos + "/META-INF/".length() == pos2) {
            return null;
        }
        String sqlPath = path.substring(pos + 1);
        String dirName = path.substring(pos + "/META-INF/".length(), pos2);
        String baseName = path.substring(pos2 + 1);
        String className = dirName.replace('/', '.') + "Test";
        String methodName = "test"
                + StringUtil.capitalize(baseName.substring(0, baseName.length()
                        - ".sql".length()));
        return new Pair<>(className, new SqlTestMethodDesc(methodName, sqlPath));
    }
}
