package org.seasar.doma.extension.gen;

import org.seasar.doma.extension.gen.dialect.GenDialect;

/**
 * SQL記述です。
 *
 * @author taedium
 */
public class SqlDesc {

  /** ファイル名 */
  protected String fileName;

  /** テンプレート名 */
  protected String templateName;

  /** エンティティ記述 */
  protected EntityDesc entityDesc;

  /**
   * 方言
   *
   * @since 1.11.0
   */
  protected GenDialect dialect;

  /**
   * ファイル名 を返します。
   *
   * @return ファイル名
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * ファイル名を設定します。
   *
   * @param fileName ファイル名
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * テンプレート名を返します。
   *
   * @return テンプレート名
   */
  public String getTemplateName() {
    return templateName;
  }

  /**
   * テンプレート名を設定します。
   *
   * @param templateName テンプレート名
   */
  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  /**
   * エンティティ記述を返します。
   *
   * @return エンティティ記述
   */
  public EntityDesc getEntityDesc() {
    return entityDesc;
  }

  /**
   * エンティティ記述を設定します。
   *
   * @param entityDesc エンティティ記述
   */
  public void setEntityDesc(EntityDesc entityDesc) {
    this.entityDesc = entityDesc;
  }

  /**
   * 方言を設定します。
   *
   * @param dialect 方言
   * @since 1.11.0
   */
  public void setDialect(GenDialect dialect) {
    this.dialect = dialect;
  }

  /**
   * 時刻のリテラルに変換します。
   *
   * @param value 値
   * @return 時刻のリテラル
   * @since 1.11.0
   */
  public String toTime(String value) {
    return dialect.convertToTimeLiteral(value);
  }

  /**
   * 日付のリテラルに変換します。
   *
   * @param value 値
   * @return 日付のリテラル
   * @since 1.11.0
   */
  public String toDate(String value) {
    return dialect.convertToDateLiteral(value);
  }

  /**
   * タイムスタンプのリテラルに変換します。
   *
   * @param value 値
   * @return タイムスタンプのリテラル
   * @since 1.11.0
   */
  public String toTimestamp(String value) {
    return dialect.convertToTimestampLiteral(value);
  }
}
