package org.seasar.doma.extension.gen;

/**
 * 識別子を生成する方法です。
 *
 * @author taedium
 */
public enum GenerationType {
  /** */
  IDENTITY(EnumConstants.GenerationType_IDENTITY),
  /** */
  SEQUENCE(EnumConstants.GenerationType_SEQUENCE),
  /** */
  TABLE(EnumConstants.GenerationType_TABLE);

  private final EnumConstants enumConstant;

  private GenerationType(EnumConstants enumConstant) {
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
}
