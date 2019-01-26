package org.seasar.doma.extension.gen.dialect;

import junit.framework.TestCase;

/** @author taedium */
public class OracleGenDialectTest extends TestCase {

  /** @throws Exception */
  public void testConvertToTimeLiteral() throws Exception {
    OracleGenDialect dialect = new OracleGenDialect();
    assertEquals("time'a'", dialect.convertToTimeLiteral("a"));
  }

  /** @throws Exception */
  public void testConvertToDateLiteral() throws Exception {
    OracleGenDialect dialect = new OracleGenDialect();
    assertEquals("date'a'", dialect.convertToDateLiteral("a"));
  }

  /** @throws Exception */
  public void testConvertToTimestampLiteral() throws Exception {
    OracleGenDialect dialect = new OracleGenDialect();
    assertEquals("timestamp'a'", dialect.convertToTimestampLiteral("a"));
  }
}
