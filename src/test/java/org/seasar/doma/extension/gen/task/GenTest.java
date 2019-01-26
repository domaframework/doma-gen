package org.seasar.doma.extension.gen.task;

import junit.framework.TestCase;

/** @author nakamura-to */
public class GenTest extends TestCase {

  public void testSetTableTypes() throws Exception {
    Gen gen = new Gen();
    gen.setTableTypes("MATERIALIZED VIEW, TABLE");
    assertEquals(2, gen.tableTypes.size());
    assertEquals("MATERIALIZED VIEW", gen.tableTypes.get(0));
    assertEquals("TABLE", gen.tableTypes.get(1));
  }
}
