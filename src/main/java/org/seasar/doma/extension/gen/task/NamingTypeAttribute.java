package org.seasar.doma.extension.gen.task;

import java.util.EnumSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.seasar.doma.extension.gen.NamingType;

/**
 * ネーミング規約を表します。
 *
 * <p>{@link NamingType} に対応するAntの列挙型です。
 *
 * @author taedium
 */
public class NamingTypeAttribute extends EnumeratedAttribute {

  @Override
  public String[] getValues() {
    EnumSet<NamingType> set = EnumSet.allOf(NamingType.class);
    String[] results = new String[set.size()];
    int index = 0;
    for (NamingType namingType : set) {
      results[index] = namingType.name().toLowerCase();
      index++;
    }
    return results;
  }

  /**
   * {@link NamingType} に変換します。
   *
   * @return 対応する {@link NamingType}
   */
  public NamingType convertToEnum() {
    if (NamingType.LOWER_CASE.name().equalsIgnoreCase(value)) {
      return NamingType.LOWER_CASE;
    }
    if (NamingType.UPPER_CASE.name().equalsIgnoreCase(value)) {
      return NamingType.UPPER_CASE;
    }
    if (NamingType.SNAKE_LOWER_CASE.name().equalsIgnoreCase(value)) {
      return NamingType.SNAKE_LOWER_CASE;
    }
    if (NamingType.SNAKE_UPPER_CASE.name().equalsIgnoreCase(value)) {
      return NamingType.SNAKE_UPPER_CASE;
    }
    if (NamingType.NONE.name().equalsIgnoreCase(value)) {
      return NamingType.NONE;
    }
    throw new AssertionError("unreachable.");
  }
}
