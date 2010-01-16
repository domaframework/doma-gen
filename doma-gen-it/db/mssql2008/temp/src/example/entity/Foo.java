/*
 * Copyright 2008-2009 ...
 * All rights reserved.
 */

package example.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Table;
import org.seasar.doma.extension.gen.it.Common;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * @author Nakamura
 */
@Entity(listener = FooListener.class, naming = NamingType.SNAKE_UPPER_CASE)
@Table(name = "foo")
public class Foo extends Common {

    /** */
    @Column(name = "bbb")
    String bbb;

    /** */
    @Column(name = "ccc")
    String ccc;

    /** 
     * Returns the bbb.
     * 
     * @return the bbb
     */
    public String getBbb() {
        return bbb;
    }

    /** 
     * Sets the bbb.
     * 
     * @param bbb the bbb
     */
    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    /** 
     * Returns the ccc.
     * 
     * @return the ccc
     */
    public String getCcc() {
        return ccc;
    }

    /** 
     * Sets the ccc.
     * 
     * @param ccc the ccc
     */
    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
}