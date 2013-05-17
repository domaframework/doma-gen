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
package org.seasar.doma.extension.gen.it;

import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.seasar.doma.wrapper.IntegerWrapper;

@javax.annotation.Generated(value = { "Doma", "1.3.2-SNAPSHOT" }, date = "2010-04-06 22:06:50")
public final class _Common extends
        org.seasar.doma.jdbc.entity.AbstractEntityType<Common> {

    static {
        org.seasar.doma.internal.Artifact.validateVersion("1.3.2-SNAPSHOT");
    }

    private static final _Common __singleton = new _Common();

    /** the version */
    public final org.seasar.doma.jdbc.entity.VersionPropertyType<Object, Common, java.lang.Integer, Object> $version = new org.seasar.doma.jdbc.entity.VersionPropertyType<Object, Common, java.lang.Integer, Object>(
            Common.class, java.lang.Integer.class, IntegerWrapper.class, null,
            null, "version", "version");

    private final org.seasar.doma.jdbc.entity.NullEntityListener __listener;

    private final org.seasar.doma.jdbc.entity.NamingType __namingType;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final String __qualifiedTableName;

    private final String __name;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __entityPropertyTypeMap;

    private _Common() {
        __listener = new org.seasar.doma.jdbc.entity.NullEntityListener();
        __namingType = org.seasar.doma.jdbc.entity.NamingType.NONE;
        __name = "Common";
        __catalogName = "";
        __schemaName = "";
        __tableName = "Common";
        __qualifiedTableName = "Common";
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __idList = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>>();
        java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __list = new java.util.ArrayList<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>>(
                1);
        java.util.Map<String, org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> __map = new java.util.HashMap<String, org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>>(
                1);
        __list.add($version);
        __map.put("version", $version);
        __idPropertyTypes = java.util.Collections.unmodifiableList(__idList);
        __entityPropertyTypes = java.util.Collections.unmodifiableList(__list);
        __entityPropertyTypeMap = java.util.Collections.unmodifiableMap(__map);
    }

    @Override
    public org.seasar.doma.jdbc.entity.NamingType getNamingType() {
        return __namingType;
    }

    @Override
    public String getName() {
        return __name;
    }

    @Override
    public String getCatalogName() {
        return __catalogName;
    }

    @Override
    public String getSchemaName() {
        return __schemaName;
    }

    @Override
    public String getTableName() {
        return __tableName;
    }

    @Override
    public String getQualifiedTableName() {
        return __qualifiedTableName;
    }

    @Override
    public void preInsert(Common entity, PreInsertContext context) {
        __listener.preInsert(entity, context);
    }

    @Override
    public void preUpdate(Common entity, PreUpdateContext context) {
        __listener.preUpdate(entity, context);
    }

    @Override
    public void preDelete(Common entity, PreDeleteContext context) {
        __listener.preDelete(entity, context);
    }

    @Override
    public void postInsert(Common entity, PostInsertContext context) {
        __listener.postInsert(entity, context);
    }

    @Override
    public void postUpdate(Common entity, PostUpdateContext context) {
        __listener.postUpdate(entity, context);
    }

    @Override
    public void postDelete(Common entity, PostDeleteContext context) {
        __listener.postDelete(entity, context);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?> getEntityPropertyType(
            String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.jdbc.entity.EntityPropertyType<Common, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.jdbc.entity.GeneratedIdPropertyType<Object, Common, ?, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.jdbc.entity.VersionPropertyType<Object, Common, ?, ?> getVersionPropertyType() {
        return $version;
    }

    @Override
    public Common newEntity() {
        return null;
    }

    @Override
    public Class<Common> getEntityClass() {
        return Common.class;
    }

    @Override
    public Common getOriginalStates(Common __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(Common __entity) {
    }

    /**
     * @return the singleton
     */
    public static _Common getSingletonInternal() {
        return __singleton;
    }

    /**
     * @return the new instance
     */
    public static _Common newInstance() {
        return new _Common();
    }

}
