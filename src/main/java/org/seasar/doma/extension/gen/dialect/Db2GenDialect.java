package org.seasar.doma.extension.gen.dialect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.seasar.doma.extension.gen.ClassConstants;
import org.seasar.doma.extension.gen.GenNullPointerException;
import org.seasar.doma.extension.gen.internal.util.JdbcUtil;

/**
 * DB2用の方言です。
 *
 * @author taedium
 */
public class Db2GenDialect extends StandardGenDialect {

  /** インスタンスを構築します。 */
  public Db2GenDialect() {
    classNameMap.put("char () for bit data", ClassConstants.bytes.getQualifiedName());
    classNameMap.put("decimal", BigDecimal.class.getName());
    classNameMap.put("long varchar for bit data", ClassConstants.bytes.getQualifiedName());
    classNameMap.put("long varchar", String.class.getName());
    classNameMap.put("varchar () for bit data", ClassConstants.bytes.getQualifiedName());
  }

  @Override
  public String getName() {
    return "db2";
  }

  @Override
  public String getDialectClassName() {
    return ClassConstants.Db2Dialect.getQualifiedName();
  }

  @Override
  public String getDefaultSchemaName(String userName) {
    return userName != null ? userName.toUpperCase() : null;
  }

  @Override
  public boolean supportsIdentity() {
    return true;
  }

  @Override
  public boolean supportsSequence() {
    return true;
  }

  @Override
  public boolean isAutoIncrement(
      Connection connection,
      String catalogName,
      String schemaName,
      String tableName,
      String columnName)
      throws SQLException {
    if (connection == null) {
      throw new GenNullPointerException("connection");
    }
    if (tableName == null) {
      throw new GenNullPointerException("tableName");
    }
    if (columnName == null) {
      throw new GenNullPointerException("columnName");
    }
    String sql =
        "select generated from syscat.columns where tabschema = ? and tabname = ? and colname = ?";
    PreparedStatement ps = JdbcUtil.prepareStatement(connection, sql);
    ps.setString(1, schemaName);
    ps.setString(2, tableName);
    ps.setString(3, columnName);
    try {
      ResultSet rs = ps.executeQuery();
      try {
        if (rs.next()) {
          String generated = rs.getString(1);
          return "A".equals(generated) || "D".equals(generated);
        }
        return false;
      } finally {
        JdbcUtil.close(rs);
      }
    } finally {
      JdbcUtil.close(ps);
    }
  }
}
