package org.seasar.doma.extension.gen.internal.util;

import junit.framework.TestCase;

/** @author taedium */
public class TableUtilTest extends TestCase {

  public void test() throws Exception {
    assertEquals("aaa.bbb.ccc", TableUtil.getQualifiedTableName("aaa", "bbb", "ccc"));
    assertEquals("bbb.ccc", TableUtil.getQualifiedTableName(null, "bbb", "ccc"));
    assertEquals("aaa.ccc", TableUtil.getQualifiedTableName("aaa", null, "ccc"));
    assertEquals("ccc", TableUtil.getQualifiedTableName(null, null, "ccc"));
  }
}
