package org.seasar.doma.extension.gen.dialect;

import org.seasar.doma.extension.gen.ClassConstants;

/** Oracle Database用の方言です。 */
public class OracleGenDialect extends Oracle11GenDialect {

  @Override
  public String getName() {
    return "oracle";
  }

  @Override
  public String getDialectClassName() {
    return ClassConstants.OracleDialect.getQualifiedName();
  }

  @Override
  public boolean supportsIdentity() {
    return true;
  }
}
