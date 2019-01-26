package org.seasar.doma.extension.gen.task;

import java.util.EnumSet;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.seasar.doma.extension.gen.GenerationType;

/**
 * 識別子の生成方法を表します。
 *
 * <p>{@link GenerationType} に対応するAntの列挙型です。
 *
 * @author taedium
 */
public class GenerationTypeAttribute extends EnumeratedAttribute {

  @Override
  public String[] getValues() {
    EnumSet<GenerationType> set = EnumSet.allOf(GenerationType.class);
    String[] results = new String[set.size()];
    int index = 0;
    for (GenerationType generationType : set) {
      results[index] = generationType.name().toLowerCase();
      index++;
    }
    return results;
  }

  /**
   * {@link GenerationType} に変換します。
   *
   * @return 対応する {@link GenerationType}
   */
  public GenerationType convertToEnum() {
    if (GenerationType.IDENTITY.name().equalsIgnoreCase(value)) {
      return GenerationType.IDENTITY;
    }
    if (GenerationType.SEQUENCE.name().equalsIgnoreCase(value)) {
      return GenerationType.SEQUENCE;
    }
    if (GenerationType.TABLE.name().equalsIgnoreCase(value)) {
      return GenerationType.TABLE;
    }
    return null;
  }
}
