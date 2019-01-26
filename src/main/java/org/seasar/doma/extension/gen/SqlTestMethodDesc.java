package org.seasar.doma.extension.gen;

/** @author nakamura-to */
public class SqlTestMethodDesc {

  protected String methodName;

  protected String path;

  /**
   * @param methodName the methodName
   * @param path the path
   */
  public SqlTestMethodDesc(String methodName, String path) {
    this.methodName = methodName;
    this.path = path;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}
