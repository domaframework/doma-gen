package org.seasar.doma.extension.gen.dialect;

import java.sql.Types;
import java.time.LocalDate;
import junit.framework.TestCase;

/** @author taedium */
public class StandardGenDialectTest extends TestCase {

  /** @throws Exception */
  public void testReplacePropertyClassName() throws Exception {
    StandardGenDialect dialect = new StandardGenDialect();
    String localDateClassName = LocalDate.class.getName();
    String utilDateClassName = java.util.Date.class.getName();
    dialect.replacePropertyClassName(localDateClassName, utilDateClassName);
    assertEquals(utilDateClassName, dialect.classNameMap.get("date"));
    assertEquals(utilDateClassName, dialect.fallbackClassNameMap.get(Types.DATE));
  }

  /** @throws Exception */
  public void testConvertToTimeLiteral() throws Exception {
    StandardGenDialect dialect = new StandardGenDialect();
    assertEquals("'a'", dialect.convertToTimeLiteral("a"));
  }

  /** @throws Exception */
  public void testConvertToDateLiteral() throws Exception {
    StandardGenDialect dialect = new StandardGenDialect();
    assertEquals("'a'", dialect.convertToDateLiteral("a"));
  }

  /** @throws Exception */
  public void testConvertToTimestampLiteral() throws Exception {
    StandardGenDialect dialect = new StandardGenDialect();
    assertEquals("'a'", dialect.convertToTimestampLiteral("a"));
  }
}
