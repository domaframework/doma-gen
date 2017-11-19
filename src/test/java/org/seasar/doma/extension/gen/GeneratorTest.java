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
import java.util.Arrays;

import junit.framework.TestCase;

import org.seasar.doma.extension.gen.dialect.GenDialect;
import org.seasar.doma.extension.gen.dialect.PostgresGenDialect;
import org.seasar.doma.extension.gen.internal.message.Message;
import org.seasar.doma.extension.gen.internal.util.ResourceUtil;

import example.hoge.CommonEntity;
import example.hoge.ParentEntity;

/**
 * @author taedium
 * 
 */
public class GeneratorTest extends TestCase {

    private GlobalFactory factory = new GlobalFactory();

    private GenDialect dialect = new PostgresGenDialect();

    private GeneratorStub generator = new GeneratorStub();

    public void testSimpleEntity() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntity_with_prefix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", null);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntity_with_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, null, "Entity");
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntity_with_prefix_and_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", "Entity");
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testOriginalStates() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, "original", false, false, true, true, true,
                false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testUseEntityListener() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, true);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testEntitySuperclass() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta name = new ColumnMeta();
        name.setComment("COMMENT for NAME");
        name.setName("NAME");
        name.setTypeName("varchar");
        name.setPrimaryKey(false);
        name.setNullable(false);

        ColumnMeta privateString = new ColumnMeta();
        privateString.setComment("COMMENT for PRIVATESTRING");
        privateString.setName("PRIVATESTRING");
        privateString.setTypeName("varchar");
        privateString.setPrimaryKey(false);
        privateString.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(name);
        tableMeta.addColumnMeta(privateString);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", CommonEntity.class,
                entityPropertyDescFactory, NamingType.NONE, null, false, false,
                true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testNamingType() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.SNAKE_UPPER_CASE, null, false, false, true, true,
                true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testShowTableName_false() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for EMP_NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");
        empName.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, false, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testShowColumnName_false() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for EMP_NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");
        empName.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, false);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testGenerationType_IDENTITY() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setAutoIncrement(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testGenerationType_SEQUENCE() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        GenerationType.SEQUENCE, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testGenerationType_TABLE() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        GenerationType.TABLE, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testCompositeId() throws Exception {
        ColumnMeta id1 = new ColumnMeta();
        id1.setComment("COMMENT for ID");
        id1.setName("ID1");
        id1.setTypeName("integer");
        id1.setPrimaryKey(true);
        id1.setNullable(false);

        ColumnMeta id2 = new ColumnMeta();
        id2.setComment("COMMENT for ID");
        id2.setName("ID2");
        id2.setTypeName("integer");
        id2.setPrimaryKey(true);
        id2.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id1);
        tableMeta.addColumnMeta(id2);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, null, null, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testEntityPropertyClassNameResolver() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta xvalue = new ColumnMeta();
        xvalue.setComment("COMMENT for XVAL");
        xvalue.setName("XVAL");
        xvalue.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(xvalue);

        File file = ResourceUtil.getResourceAsFile(getClass().getPackage()
                .getName().replace(".", "/")
                + "/entityPropertyClassNames.properties");
        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(file);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntityListener() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        EntityListenerDescFactory entityListenerDescFactory = factory
                .createEntityListenerDescFactory("example.entity", null);
        EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                .createEntityListenerDesc(entityDesc);
        generator.generate(new EntityListenerContext(entityListenerDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntityListener_with_prefix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", null);
        EntityListenerDescFactory entityListenerDescFactory = factory
                .createEntityListenerDescFactory("example.entity", null);
        EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                .createEntityListenerDesc(entityDesc);
        generator.generate(new EntityListenerContext(entityListenerDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntityListener_with_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, null, "Entity");
        EntityListenerDescFactory entityListenerDescFactory = factory
                .createEntityListenerDescFactory("example.entity", null);
        EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                .createEntityListenerDesc(entityDesc);
        generator.generate(new EntityListenerContext(entityListenerDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleEntityListener_with_prefix_and_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", "Entity");
        EntityListenerDescFactory entityListenerDescFactory = factory
                .createEntityListenerDescFactory("example.entity", null);
        EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                .createEntityListenerDesc(entityDesc);
        generator.generate(new EntityListenerContext(entityListenerDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testExtendingEntityListener() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        EntityListenerDescFactory entityListenerDescFactory = factory
                .createEntityListenerDescFactory("example.entity",
                        "aaa.FooListener");
        EntityListenerDesc entityListenerDesc = entityListenerDescFactory
                .createEntityListenerDesc(entityDesc);
        generator.generate(new EntityListenerContext(entityListenerDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleDao() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        DaoDescFactory daoDescFactory = factory.createDaoDescFactory(
                "example.dao", "Dao", "dao.config.MyConfig");
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleDao_with_prefix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", null);

        DaoDescFactory daoDescFactory = factory.createDaoDescFactory(
                "example.dao", "Dao", "dao.config.MyConfig");
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleDao_with_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, null, "Entity");

        DaoDescFactory daoDescFactory = factory.createDaoDescFactory(
                "example.dao", "Dao", "dao.config.MyConfig");
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSimpleDao_with_prefix_and_suffix() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta, "T", "Entity");

        DaoDescFactory daoDescFactory = factory.createDaoDescFactory(
                "example.dao", "Dao", "dao.config.MyConfig");
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testDefaultConfigDao() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        DaoDescFactory daoDescFactory = factory.createDaoDescFactory(
                "example.dao", "Dao", null);
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");
        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_entitySuperclass() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", ParentEntity.class,
                entityPropertyDescFactory, NamingType.NONE, null, false, false,
                true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");
        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_entitySuperclass_columnNotFound()
            throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("NAME");
        empName.setTypeName("varcar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", ParentEntity.class,
                entityPropertyDescFactory, NamingType.NONE, null, false, false,
                true, true, true, false);

        try {
            entityDescFactory.createEntityDesc(tableMeta);
            fail();
        } catch (GenException e) {
            System.out.println(e.getMessage());
            assertEquals(Message.DOMAGEN0021, e.getMessageResource());
        }
    }

    public void testSimpleSqlTest() throws Exception {
        SqlTestMethodDesc select = new SqlTestMethodDesc("testSelect",
                "META-INF/" + getClass().getName().replace(".", "/")
                        + "/select.sql");
        SqlTestMethodDesc insert = new SqlTestMethodDesc("testInsert",
                "META-INF/" + getClass().getName().replace(".", "/")
                        + "/insert.sql");
        SqlTestMethodDesc update = new SqlTestMethodDesc("testUpdate",
                "META-INF/" + getClass().getName().replace(".", "/")
                        + "/update.sql");
        SqlTestCaseDescFactory sqlFileTestDescFactory = factory
                .createSqlTestCaseDescFactory(
                        "org.seasar.doma.jdbc.dialect.StandardDialect",
                        "org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:example",
                        "sa", "");
        SqlTestCaseDesc sqlTestCaseDesc = sqlFileTestDescFactory.createSqlFileTestDesc(
                "example.dao.SqlTest", Arrays.asList(select, insert, update));
        generator.generate(new SqlTestContext(sqlTestCaseDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_number() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("int4");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_varchar() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("varchar");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_time() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("time");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_date() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("date");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectById_timestamp() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("timestamp");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varchar");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectById.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    public void testSelectByIdAndVersion() throws Exception {
        ColumnMeta id = new ColumnMeta();
        id.setComment("COMMENT for ID");
        id.setName("ID");
        id.setTypeName("integer");
        id.setPrimaryKey(true);
        id.setNullable(false);

        ColumnMeta empName = new ColumnMeta();
        empName.setComment("COMMENT for NAME");
        empName.setName("EMP_NAME");
        empName.setTypeName("varcar");

        ColumnMeta version = new ColumnMeta();
        version.setComment("COMMENT for VERSION");
        version.setName("VERSION");
        version.setTypeName("integer");

        TableMeta tableMeta = new TableMeta();
        tableMeta.setCatalogName("CATALOG");
        tableMeta.setSchemaName("SCHEMA");
        tableMeta.setName("HOGE");
        tableMeta.setComment("COMMENT for HOGE");
        tableMeta.addColumnMeta(id);
        tableMeta.addColumnMeta(empName);
        tableMeta.addColumnMeta(version);

        EntityPropertyClassNameResolver resolver = factory
                .createEntityPropertyClassNameResolver(null);
        EntityPropertyDescFactory entityPropertyDescFactory = factory
                .createEntityPropertyDescFactory(dialect, resolver, "version",
                        null, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory.createEntityDescFactory(
                "example.entity", null, entityPropertyDescFactory,
                NamingType.NONE, null, false, false, true, true, true, false);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);

        SqlDescFactory sqlDescFactory = factory.createSqlDescFactory(null,
                dialect);
        SqlDesc sqlDesc = sqlDescFactory.createSqlDesc(entityDesc, "dummy",
                "selectByIdAndVersion.sql.ftl");

        generator.generate(new SqlContext(sqlDesc));

        assertEquals(expect(), generator.getResult());
    }

    private String expect() {
        System.out.println(generator.getResult());

        String path = getClass().getName().replace(".", "/") + "_"
                + getName().substring(4) + ".txt";
        return ResourceUtil.getResourceAsString(path);
    }

    private class EntityContext extends GenerationContext {

        public EntityContext(EntityDesc model) {
            super(model, new File("dummy"), model.getTemplateName(), "UTF-8",
                    true);
        }
    }

    private class EntityListenerContext extends GenerationContext {

        public EntityListenerContext(EntityListenerDesc model) {
            super(model, new File("dummy"), model.getTemplateName(), "UTF-8",
                    true);
        }
    }

    private class DaoContext extends GenerationContext {

        public DaoContext(DaoDesc model) {
            super(model, new File("dummy"), model.getTemplateName(), "UTF-8",
                    true);
        }
    }

    private class SqlTestContext extends GenerationContext {

        public SqlTestContext(SqlTestCaseDesc model) {
            super(model, new File("dummy"), model.getTemplateName(), "UTF-8",
                    true);
        }
    }

    private class SqlContext extends GenerationContext {

        public SqlContext(SqlDesc model) {
            super(model, new File("dummy"), model.getTemplateName(), "UTF-8",
                    true);
        }
    }
}
