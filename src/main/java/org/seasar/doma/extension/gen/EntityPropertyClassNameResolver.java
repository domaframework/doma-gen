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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.IOUtil;

/**
 * エンティティプロパティのクラス名リゾルバです。
 * 
 * @author taedium
 * 
 */
public class EntityPropertyClassNameResolver {

    /** エンティティプロパティ名の正規表現を表すパターンをキー、クラス名を値とするマップ */
    protected final LinkedHashMap<Pattern, String> patternMap;

    /**
     * インスタンスを構築します。
     * 
     * @param propertyFile
     *            プロパティファイル
     */
    public EntityPropertyClassNameResolver(File propertyFile) {
        if (propertyFile == null) {
            patternMap = new LinkedHashMap<Pattern, String>();
        } else {
            patternMap = load(propertyFile);
        }
    }

    /**
     * プロパティファイルをロードします。
     * 
     * @param propertyFile
     *            プロパティファイル
     * @return エンティティプロパティ名の正規表現を表すパターンをキー、クラス名を値とするマップ
     */
    protected LinkedHashMap<Pattern, String> load(File propertyFile) {
        LinkedHashMap<Pattern, String> result = new LinkedHashMap<Pattern, String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(propertyFile), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                char firstChar = line.charAt(0);
                if (firstChar == '#' || firstChar == '!') {
                    continue;
                }
                int pos = line.indexOf('=');
                if (pos < 0) {
                    continue;
                }
                String key = line.substring(0, pos);
                String value = line.substring(pos + 1, line.length());
                int pos2 = line.indexOf('@');
                if (pos2 == 0) {
                    key = key.substring(1);
                } else if (pos2 > 0) {
                    key = Pattern.quote(key.substring(0, pos2))
                            + key.substring(pos2);
                }
                result.put(Pattern.compile(key), value);
            }
        } catch (IOException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        } finally {
            IOUtil.close(reader);
        }
        return result;
    }

    /**
     * エンティティプロパティのクラス名を解決します。
     * 
     * @param entityDesc
     *            エンティティ記述
     * @param propertyName
     *            エンティティプロパティ名
     * @param defaultPropertyClassName
     *            エンティティプロパティのデフォルトのクラス名
     * @return エンティティプロパティのクラス名
     */
    public String resolve(EntityDesc entityDesc, String propertyName,
            String defaultPropertyClassName) {
        String qualifiedPropertyName = entityDesc.getQualifiedName() + "@"
                + propertyName;
        for (Map.Entry<Pattern, String> entry : patternMap.entrySet()) {
            Pattern pattern = entry.getKey();
            String input = pattern.pattern().contains("@") ? qualifiedPropertyName
                    : propertyName;
            Matcher matcher = pattern.matcher(input);
            if (!matcher.matches()) {
                continue;
            }
            matcher.reset();
            StringBuffer buf = new StringBuffer();
            String replacement = entry.getValue();
            for (; matcher.find();) {
                matcher.appendReplacement(buf, replacement);
                if (matcher.hitEnd()) {
                    break;
                }
            }
            matcher.appendTail(buf);
            return buf.toString();
        }
        return defaultPropertyClassName;
    }
}
