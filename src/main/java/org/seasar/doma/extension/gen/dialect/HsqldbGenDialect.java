package org.seasar.doma.extension.gen.dialect;

import org.seasar.doma.extension.gen.ClassConstants;

/**
 * HSQLDB用の方言です。
 *
 * @author taedium
 */
public class HsqldbGenDialect extends StandardGenDialect {

  /** インスタンスを構築します。 */
  public HsqldbGenDialect() {
    classNameMap.put("int", Integer.class.getName());
    classNameMap.put("varchar_ignorecase", String.class.getName());
  }

  @Override
  public String getName() {
    return "hsqldb";
  }

  @Override
  public String getDialectClassName() {
    return ClassConstants.HsqldbDialect.getQualifiedName();
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
