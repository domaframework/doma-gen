package example.entity;

/** */
@javax.annotation.Generated(value = { "Doma", "0.9.13-SNAPSHOT" }, date = "2010-01-16 15:58:31")
public final class _Foo implements org.seasar.doma.internal.jdbc.entity.EntityType<example.entity.Foo> {

    private static final _Foo __singleton = new _Foo();

    /** the bbb */
    public final org.seasar.doma.internal.jdbc.entity.BasicPropertyType<example.entity.Foo, java.lang.String> bbb = new org.seasar.doma.internal.jdbc.entity.BasicPropertyType<example.entity.Foo, java.lang.String>("bbb", "bbb", true, true) {
        @Override
        public org.seasar.doma.wrapper.StringWrapper getWrapper(example.entity.Foo entity) {
            return new Wrapper(entity);
        }

        class Wrapper extends org.seasar.doma.wrapper.StringWrapper {

            private final example.entity.Foo entity;

            private Wrapper(example.entity.Foo entity) {
                this.entity = entity;
            }

            @Override
            protected java.lang.String doGet() {
                if (entity == null) {
                    return null;
                }
                return entity.bbb;
            }

            @Override
            protected void doSet(java.lang.String value) {
                if (entity == null) {
                    return;
                }
                entity.bbb = value;
            }
        }
    };

    /** the ccc */
    public final org.seasar.doma.internal.jdbc.entity.BasicPropertyType<example.entity.Foo, java.lang.String> ccc = new org.seasar.doma.internal.jdbc.entity.BasicPropertyType<example.entity.Foo, java.lang.String>("ccc", "ccc", true, true) {
        @Override
        public org.seasar.doma.wrapper.StringWrapper getWrapper(example.entity.Foo entity) {
            return new Wrapper(entity);
        }

        class Wrapper extends org.seasar.doma.wrapper.StringWrapper {

            private final example.entity.Foo entity;

            private Wrapper(example.entity.Foo entity) {
                this.entity = entity;
            }

            @Override
            protected java.lang.String doGet() {
                if (entity == null) {
                    return null;
                }
                return entity.ccc;
            }

            @Override
            protected void doSet(java.lang.String value) {
                if (entity == null) {
                    return;
                }
                entity.ccc = value;
            }
        }
    };

    private final example.entity.FooListener __listener;

    private final org.seasar.doma.jdbc.entity.NamingType __namingType;

    private final String __catalogName;

    private final String __schemaName;

    private final String __tableName;

    private final String __qualifiedTableName;

    private final String __name;

    private final java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __idPropertyTypes;

    private final java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __entityPropertyTypes;

    private final java.util.Map<String, org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __entityPropertyTypeMap;

    private _Foo() {
        __listener = new example.entity.FooListener();
        __namingType = org.seasar.doma.jdbc.entity.NamingType.SNAKE_UPPER_CASE;
        __name = "Foo";
        __catalogName = "";
        __schemaName = "";
        __tableName = "foo";
        __qualifiedTableName = "foo";
        java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __idList = new java.util.ArrayList<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>>();
        java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __list = new java.util.ArrayList<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>>(2);
        java.util.Map<String, org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> __map = new java.util.HashMap<String, org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>>(2);
        __list.add(bbb);
        __map.put("bbb", bbb);
        __list.add(ccc);
        __map.put("ccc", ccc);
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
    public void preInsert(example.entity.Foo entity) {
        __listener.preInsert(entity);
    }

    @Override
    public void preUpdate(example.entity.Foo entity) {
        __listener.preUpdate(entity);
    }

    @Override
    public void preDelete(example.entity.Foo entity) {
        __listener.preDelete(entity);
    }

    @Override
    public java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> getEntityPropertyTypes() {
        return __entityPropertyTypes;
    }

    @Override
    public org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?> getEntityPropertyType(String __name) {
        return __entityPropertyTypeMap.get(__name);
    }

    @Override
    public java.util.List<org.seasar.doma.internal.jdbc.entity.EntityPropertyType<example.entity.Foo, ?>> getIdPropertyTypes() {
        return __idPropertyTypes;
    }

    @Override
    public org.seasar.doma.internal.jdbc.entity.GeneratedIdPropertyType<example.entity.Foo, ?> getGeneratedIdPropertyType() {
        return null;
    }

    @Override
    public org.seasar.doma.internal.jdbc.entity.VersionPropertyType<example.entity.Foo, ?> getVersionPropertyType() {
        return null;
    }

    @Override
    public example.entity.Foo newEntity() {
        return new example.entity.Foo();
    }

    @Override
    public Class<example.entity.Foo> getEntityClass() {
        return example.entity.Foo.class;
    }

    @Override
    public example.entity.Foo getOriginalStates(example.entity.Foo __entity) {
        return null;
    }

    @Override
    public void saveCurrentStates(example.entity.Foo __entity) {
    }

    /**
     * @return the singleton
     */
    public static _Foo get() {
        return __singleton;
    }

}
