package org.seasar.doma.extension.gen;

/**
 * Dao記述です。
 *
 * @author taedium
 */
public class DaoDesc extends ClassDesc {

  /** 設定クラスの単純名 */
  protected String configClassSimpleName;

  /** エンティティ記述 */
  protected EntityDesc entityDesc;

  /** テンプレート名 */
  protected String templateName;

  /**
   * 設定クラスの単純名を返します。
   *
   * @return 設定クラスの単純名
   */
  public String getConfigClassSimpleName() {
    return configClassSimpleName;
  }

  /**
   * 設定クラスの単純名を設定します。
   *
   * @param configClassSimpleName 設定クラスの単純名
   */
  public void setConfigClassSimpleName(String configClassSimpleName) {
    this.configClassSimpleName = configClassSimpleName;
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
   * テンプレート名を設定します。
   *
   * @param templateName テンプレート名
   */
  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  /**
   * テンプレート名を返します。
   *
   * @return テンプレート名
   */
  public String getTemplateName() {
    return templateName;
  }
}
