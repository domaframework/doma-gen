package org.seasar.doma.extension.gen.task;

import java.util.List;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.seasar.doma.extension.gen.dialect.GenDialectRegistry;

/**
 * 方言名を表します。
 *
 * <p>方言に対応するAntの列挙型です。
 *
 * @author taedium
 */
public class DialectNameAttribute extends EnumeratedAttribute {

  @Override
  public String[] getValues() {
    List<String> dialectNames = GenDialectRegistry.getDialectNames();
    String[] results = new String[dialectNames.size()];
    int index = 0;
    for (String dialectName : dialectNames) {
      results[index] = dialectName;
      index++;
    }
    return results;
  }
}
