package org.seasar.doma.extension.gen;

import org.seasar.doma.extension.gen.internal.util.StringUtil;

/**
 * ネーミング規約を表します。
 *
 * @author taedium
 */
public enum NamingType {

  /** */
  NONE(EnumConstants.NamingType_NONE) {

    @Override
    public String apply(String text) {
      return text;
    }
  },
  /** */
  LOWER_CASE(EnumConstants.NamingType_LOWER_CASE) {

    @Override
    public String apply(String text) {
      if (text == null) {
        throw new GenNullPointerException("text");
      }
      return text.toLowerCase();
    }
  },
  /** */
  UPPER_CASE(EnumConstants.NamingType_UPPER_CASE) {

    @Override
    public String apply(String text) {
      if (text == null) {
        throw new GenNullPointerException("text");
      }
      return text.toUpperCase();
    }
  },
  /** */
  SNAKE_UPPER_CASE(EnumConstants.NamingType_SNAKE_UPPER_CASE) {

    @Override
    public String apply(String text) {
      if (text == null) {
        throw new GenNullPointerException("text");
      }
      String s = StringUtil.fromCamelCaseToSnakeCase(text);
      return s.toUpperCase();
    }
  },
  /** */
  SNAKE_LOWER_CASE(EnumConstants.NamingType_SNAKE_LOWER_CASE) {

    @Override
    public String apply(String text) {
      if (text == null) {
        throw new GenNullPointerException("text");
      }
      String s = StringUtil.fromCamelCaseToSnakeCase(text);
      return s.toLowerCase();
    }
  };

  private final EnumConstants enumConstant;

  private NamingType(EnumConstants enumConstant) {
    this.enumConstant = enumConstant;
  }

  /**
   * 列挙定数を返します。
   *
   * @return 列挙定数
   */
  public EnumConstants getEnumConstant() {
    return enumConstant;
  }

  /**
   * 参照名を返します。
   *
   * @return 参照名
   */
  public String getReferenceName() {
    return enumConstant.getReferenceName();
  }

  /**
   * ネーミング規約を適用します。
   *
   * @param text 規約が適用される文字列
   * @return 規約が適用された文字列
   */
  public abstract String apply(String text);
}
