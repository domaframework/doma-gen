package example.dao;

/** */
@javax.annotation.Generated(value = { "Doma", "0.9.13-SNAPSHOT" }, date = "2010-01-16 15:58:31")
public class FooDaoImpl extends org.seasar.doma.internal.jdbc.dao.AbstractDao implements example.dao.FooDao {

    /**
     * @param config the config
     */
    public FooDaoImpl(org.seasar.doma.jdbc.Config config) {
        super(new org.seasar.doma.jdbc.ConfigProxy(config));
    }

    @Override
    public int insert(example.entity.Foo entity) {
        entering("example.dao.FooDaoImpl", "insert", entity);
        if (entity == null) {
            throw new org.seasar.doma.DomaNullPointerException("entity");
        }
        org.seasar.doma.internal.jdbc.query.AutoInsertQuery<example.entity.Foo> __query = new org.seasar.doma.internal.jdbc.query.AutoInsertQuery<example.entity.Foo>(example.entity._Foo.get());
        __query.setConfig(config);
        __query.setEntity(entity);
        __query.setCallerClassName("example.dao.FooDaoImpl");
        __query.setCallerMethodName("insert");
        __query.setQueryTimeout(-1);
        __query.setNullExcluded(false);
        __query.setIncludedPropertyNames();
        __query.setExcludedPropertyNames();
        __query.prepare();
        org.seasar.doma.internal.jdbc.command.InsertCommand __command = new org.seasar.doma.internal.jdbc.command.InsertCommand(__query);
        int __result = __command.execute();
        __query.complete();
        exiting("example.dao.FooDaoImpl", "insert", __result);
        return __result;
    }

    @Override
    public int update(example.entity.Foo entity) {
        entering("example.dao.FooDaoImpl", "update", entity);
        if (entity == null) {
            throw new org.seasar.doma.DomaNullPointerException("entity");
        }
        org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<example.entity.Foo> __query = new org.seasar.doma.internal.jdbc.query.AutoUpdateQuery<example.entity.Foo>(example.entity._Foo.get());
        __query.setConfig(config);
        __query.setEntity(entity);
        __query.setCallerClassName("example.dao.FooDaoImpl");
        __query.setCallerMethodName("update");
        __query.setQueryTimeout(-1);
        __query.setNullExcluded(false);
        __query.setVersionIncluded(false);
        __query.setIncludedPropertyNames();
        __query.setExcludedPropertyNames();
        __query.setUnchangedPropertyIncluded(false);
        __query.setOptimisticLockExceptionSuppressed(false);
        __query.prepare();
        org.seasar.doma.internal.jdbc.command.UpdateCommand __command = new org.seasar.doma.internal.jdbc.command.UpdateCommand(__query);
        int __result = __command.execute();
        __query.complete();
        exiting("example.dao.FooDaoImpl", "update", __result);
        return __result;
    }

    @Override
    public int delete(example.entity.Foo entity) {
        entering("example.dao.FooDaoImpl", "delete", entity);
        if (entity == null) {
            throw new org.seasar.doma.DomaNullPointerException("entity");
        }
        org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<example.entity.Foo> __query = new org.seasar.doma.internal.jdbc.query.AutoDeleteQuery<example.entity.Foo>(example.entity._Foo.get());
        __query.setConfig(config);
        __query.setEntity(entity);
        __query.setCallerClassName("example.dao.FooDaoImpl");
        __query.setCallerMethodName("delete");
        __query.setQueryTimeout(-1);
        __query.setVersionIgnored(false);
        __query.setOptimisticLockExceptionSuppressed(false);
        __query.prepare();
        org.seasar.doma.internal.jdbc.command.DeleteCommand __command = new org.seasar.doma.internal.jdbc.command.DeleteCommand(__query);
        int __result = __command.execute();
        __query.complete();
        exiting("example.dao.FooDaoImpl", "delete", __result);
        return __result;
    }

}
