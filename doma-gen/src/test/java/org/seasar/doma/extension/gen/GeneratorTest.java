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
package org.seasar.doma.extension.gen;

import java.io.File;

import junit.framework.TestCase;

import org.seasar.doma.extension.gen.dialect.Dialect;
import org.seasar.doma.extension.gen.dialect.PostgresDialect;

/**
 * @author taedium
 * 
 */
public class GeneratorTest extends TestCase {

    private GlobalFactory factory = new GlobalFactory();

    private Dialect dialect = new PostgresDialect();

    private GeneratorStub generator = new GeneratorStub();

    public void test() throws Exception {
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
                .createEntityPropertyDescFactory(dialect, resolver, "version", NamingType.SNAKE_UPPER_CASE, GenerationType.SEQUENCE, 100L, 50L, true);
        EntityDescFactory entityDescFactory = factory
                .createEntityDescFactory("entity", "entity.common.Commmon", "entity.listener.CommonListener", entityPropertyDescFactory, NamingType.SNAKE_UPPER_CASE, false, false, true, true);
        EntityDesc entityDesc = entityDescFactory.createEntityDesc(tableMeta);
        generator.generate(new EntityContext(entityDesc));
        System.out.println(generator.writer);

        DaoDescFactory daoDescFactory = factory
                .createDaoDescFactory("dao", "Dao", "dao.config.MyConfig");
        DaoDesc daoDesc = daoDescFactory.createDaoDesc(entityDesc);
        generator.generate(new DaoContext(daoDesc));
        System.out.println(generator.writer);
    }

    private class EntityContext extends GenerationContext {

        public EntityContext(EntityDesc model) {
            super(model, new File("dummy"), "entity.ftl", "UTF-8", true);
        }
    }

    private class DaoContext extends GenerationContext {

        public DaoContext(DaoDesc model) {
            super(model, new File("dummy"), "dao.ftl", "UTF-8", true);
        }
    }
}
