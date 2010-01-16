/*
 * Copyright 2008-2009 ...
 * All rights reserved.
 */

package example.dao;

import example.entity.Foo;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.jdbc.ConfigProxy;

/**
 * @author Nakamura
 */
@Dao(config = ConfigProxy.class)
public interface FooDao {

    /**
     * @return affected rows
     */
    @Insert
    int insert(Foo entity);

    /**
     * @return affected rows
     */
    @Update
    int update(Foo entity);

    /**
     * @return affected rows
     */
    @Delete
    int delete(Foo entity);
}