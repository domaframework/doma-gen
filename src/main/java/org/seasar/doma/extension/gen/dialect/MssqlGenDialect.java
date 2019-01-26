package org.seasar.doma.extension.gen.dialect;

import org.seasar.doma.extension.gen.ClassConstants;

/**
 * SQL Server用の方言です。
 *
 * @author taedium
 * @since 1.30.0
 */
public class MssqlGenDialect extends Mssql2008GenDialect {

  /** インスタンスを構築します。 */
  public MssqlGenDialect() {}

  @Override
  public String getName() {
    return "mssql";
  }

  @Override
  public String getDialectClassName() {
    return ClassConstants.Mssql.getQualifiedName();
  }

  @Override
  public boolean supportsSequence() {
    return true;
  }
}
