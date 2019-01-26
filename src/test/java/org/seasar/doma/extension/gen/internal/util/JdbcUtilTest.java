package org.seasar.doma.extension.gen.internal.util;

import junit.framework.TestCase;

/** @author nakamura-to */
public class JdbcUtilTest extends TestCase {

  public void testInferDialectName() throws Exception {
    String dialectName = JdbcUtil.inferDialectName("jdbc:postgresql://localhost/hoge");
    assertEquals("postgres", dialectName);
  }

  public void testInferDialectName_mysql() throws Exception {
    String dialectName = JdbcUtil.inferDialectName("jdbc:mysql://localhost:3306/hoge");
    assertEquals("mysql", dialectName);
  }

  public void testInferDialectName_unknown() throws Exception {
    String dialectName = JdbcUtil.inferDialectName("jdbc:unknown://localhost/hoge");
    assertNull(dialectName);
  }

  public void testInferDialectName_invalidUrl() throws Exception {
    String dialectName = JdbcUtil.inferDialectName("localhost/hoge");
    assertNull(dialectName);
  }

  public void testInferDriverClassName() throws Exception {
    String driverClassName = JdbcUtil.inferDriverClassName("jdbc:postgresql://localhost/hoge");
    assertEquals("org.postgresql.Driver", driverClassName);
  }

  public void testInferDriverClassName_mysql() throws Exception {
    String driverClassName = JdbcUtil.inferDriverClassName("jdbc:mysql://localhost:3306/hoge");
    assertEquals("com.mysql.jdbc.Driver", driverClassName);
  }

  public void testInferDriverClassName_unknown() throws Exception {
    String driverClassName = JdbcUtil.inferDriverClassName("jdbc:unknown://localhost/hoge");
    assertNull(driverClassName);
  }

  public void testInferDriverClassName_invalidUrl() throws Exception {
    String driverClassName = JdbcUtil.inferDriverClassName("localhost/hoge");
    assertNull(driverClassName);
  }
}
