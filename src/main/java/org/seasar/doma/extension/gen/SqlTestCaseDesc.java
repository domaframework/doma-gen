package org.seasar.doma.extension.gen;

import java.sql.Driver;
import java.util.List;

/**
 * SQLテストケース記述です。
 *
 * @author taedium
 */
public class SqlTestCaseDesc extends ClassDesc {

  /** テンプレート名 */
  protected String templateName;

  /** {@code org.seasar.doma.jdbc.dialect.Dialect} のサブタイプのクラス名 */
  protected String dialectClassName = null;

  /** {@link Driver} のサブタイプのクラス名 */
  protected String driverClassName = null;

  /** JDBC接続ユーザー */
  protected String user = null;

  /** JDBC接続パスワード */
  protected String password = null;

  /** JDBC接続URL */
  protected String url = null;

  /** 抽象クラスの場合 {@code true} */
  protected boolean abstrct;

  /** メソッド記述のリスト */
  protected List<SqlTestMethodDesc> methodDescs;

  /** @return the templateName */
  public String getTemplateName() {
    return templateName;
  }

  /** @param templateName the templateName to set */
  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  /** @return the driverClassName */
  public String getDriverClassName() {
    return driverClassName;
  }

  /** @param driverClassName the driverClassName to set */
  public void setDriverClassName(String driverClassName) {
    this.driverClassName = driverClassName;
  }

  /** @return the user */
  public String getUser() {
    return user;
  }

  /** @param user the user to set */
  public void setUser(String user) {
    this.user = user;
  }

  /** @return the password */
  public String getPassword() {
    return password;
  }

  /** @param password the password to set */
  public void setPassword(String password) {
    this.password = password;
  }

  /** @return the url */
  public String getUrl() {
    return url;
  }

  /** @param url the url to set */
  public void setUrl(String url) {
    this.url = url;
  }

  /** @return the abstrct */
  public boolean isAbstrct() {
    return abstrct;
  }

  /** @param abstrct the abstrct to set */
  public void setAbstrct(boolean abstrct) {
    this.abstrct = abstrct;
  }

  /** @return the dialectClassName */
  public String getDialectClassName() {
    return dialectClassName;
  }

  /** @param dialectClassName the dialectClassName to set */
  public void setDialectClassName(String dialectClassName) {
    this.dialectClassName = dialectClassName;
  }

  /** @return the methodDescs */
  public List<SqlTestMethodDesc> getMethodDescs() {
    return methodDescs;
  }

  /** @param methodDescs the methodDescs to set */
  public void setMethodDescs(List<SqlTestMethodDesc> methodDescs) {
    this.methodDescs = methodDescs;
  }
}
