package org.seasar.doma.extension.gen;

/**
 * 列挙定数です。
 *
 * @author taedium
 */
public enum EnumConstants {

  /** */
  NamingType_NONE(ClassConstants.NamingType, "NONE"),
  /** */
  NamingType_UPPER_CASE(ClassConstants.NamingType, "UPPER_CASE"),
  /** */
  NamingType_LOWER_CASE(ClassConstants.NamingType, "LOWER_CASE"),
  /** */
  NamingType_SNAKE_UPPER_CASE(ClassConstants.NamingType, "SNAKE_UPPER_CASE"),
  /** */
  NamingType_SNAKE_LOWER_CASE(ClassConstants.NamingType, "SNAKE_LOWER_CASE"),
  /** */
  GenerationType_SEQUENCE(ClassConstants.GenerationType, "SEQUENCE"),
  /** */
  GenerationType_TABLE(ClassConstants.GenerationType, "TABLE"),
  /** */
  GenerationType_IDENTITY(ClassConstants.GenerationType, "IDENTITY"),
  ;

  private final ClassConstants classConstant;

  private final String name;

  private EnumConstants(ClassConstants classConstant, String name) {
    this.classConstant = classConstant;
    this.name = name;
  }

  /**
   * クラス定数を返します。
   *
   * @return クラス定数
   */
  public ClassConstants getClassConstant() {
    return classConstant;
  }

  /**
   * インポート名を返します。
   *
   * @return インポート名
   */
  public String getImportName() {
    return classConstant.getQualifiedName();
  }

  /**
   * 参照名を返します。
   *
   * @return 参照名
   */
  public String getReferenceName() {
    return classConstant.getSimpleName() + "." + name;
  }

  /**
   * 名前を返します。
   *
   * @return 名前
   */
  public String getName() {
    return name;
  }
}
