package org.seasar.doma.extension.gen.task;

import java.io.File;
import org.apache.tools.ant.types.DataType;

/**
 * Daoの設定です。
 *
 * @author taedium
 */
public class DaoConfig extends DataType {

  /** DaoのJavaコードを生成する場合 {@code true} */
  protected boolean generate = true;

  /** 同名のDaoインタフェースのJavaファイルを上書きする場合{@code true}、しない場合{@code false} */
  protected boolean overwrite = false;

  /** 生成されるJavaファイルの出力先ディレクトリ */
  protected File destDir = null;

  /** Javaファイルのエンコーディング */
  protected String encoding = "UTF-8";

  /** Daoインタフェースのパッケージ名 */
  protected String packageName = "example.dao";

  /** Daoインタフェースのサフィックス */
  protected String suffix = "Dao";

  /** 設定クラス名 */
  protected String configClassName = null;

  /** ベースディレクトリ */
  protected File baseDir = null;

  public boolean isGenerate() {
    return generate;
  }

  public void setGenerate(boolean generate) {
    this.generate = generate;
  }

  public boolean isOverwrite() {
    return overwrite;
  }

  public void setOverwrite(boolean overwrite) {
    this.overwrite = overwrite;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getConfigClassName() {
    return configClassName;
  }

  public void setConfigClassName(String configClassName) {
    this.configClassName = configClassName;
  }

  public File getDestDir() {
    if (destDir == null) {
      destDir = new File(baseDir != null ? baseDir : getProject().getBaseDir(), "src/main/java");
    }
    return destDir;
  }

  public void setDestDir(File destDir) {
    this.destDir = destDir;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  /** @param baseDir the baseDir to set */
  protected void setBaseDir(File baseDir) {
    this.baseDir = baseDir;
  }
}
