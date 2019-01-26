package org.seasar.doma.extension.gen.internal.util;

import junit.framework.TestCase;

/** @author taedium */
public class StringUtilTest extends TestCase {

  public void testCapitalize() throws Exception {
    assertEquals("Aaa", StringUtil.capitalize("aaa"));
  }

  public void testDecapitalize() throws Exception {
    assertEquals("aAA", StringUtil.decapitalize("AAA"));
  }

  public void testFromSnakeCaseToCamelCase() throws Exception {
    assertEquals("aaaBbb", StringUtil.fromSnakeCaseToCamelCase("AAA_BBB"));
  }

  public void testFromCamelCaseToSnakeCase() throws Exception {
    assertEquals("aaa_bbb", StringUtil.fromCamelCaseToSnakeCase("aaaBbb"));
  }

  public void testIsNullOrEmpty() throws Exception {
    assertTrue(StringUtil.isNullOrEmpty(null));
    assertTrue(StringUtil.isNullOrEmpty(""));
    assertFalse(StringUtil.isNullOrEmpty(" "));
  }
}
