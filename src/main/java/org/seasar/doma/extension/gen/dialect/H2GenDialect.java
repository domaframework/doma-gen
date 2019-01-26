package org.seasar.doma.extension.gen.dialect;

import org.seasar.doma.extension.gen.ClassConstants;

/**
 * H2 Database Engine用の方言です。
 *
 * @author taedium
 */
public class H2GenDialect extends StandardGenDialect {

  /** インスタンスを構築します。 */
  public H2GenDialect() {}

  @Override
  public String getName() {
    return "h2";
  }

  @Override
  public String getDialectClassName() {
    return ClassConstants.H2Dialect.getQualifiedName();
  }

  @Override
  public String getDefaultSchemaName(String userName) {
    return null;
  }

  @Override
  public boolean isJdbcCommentUnavailable() {
    return true;
  }

  @Override
  public boolean supportsIdentity() {
    return true;
  }

  @Override
  public boolean supportsSequence() {
    return true;
  }
}
