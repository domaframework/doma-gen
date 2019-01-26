package org.seasar.doma.extension.gen.internal.util;

/** @author taedium */
public final class TableUtil {

  public static String getQualifiedTableName(
      String catalogName, String schemaName, String tableName) {
    StringBuilder buf = new StringBuilder();
    if (catalogName != null && !catalogName.isEmpty()) {
      buf.append(catalogName).append(".");
    }
    if (schemaName != null && !schemaName.isEmpty()) {
      buf.append(schemaName).append(".");
    }
    return buf.append(tableName).toString();
  }
}
