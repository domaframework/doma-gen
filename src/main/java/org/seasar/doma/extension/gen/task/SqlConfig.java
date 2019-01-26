package org.seasar.doma.extension.gen.task;

import java.io.File;
import org.apache.tools.ant.types.DataType;

/**
 * SQLの設定です。
 *
 * @author taedium
 */
public class SqlConfig extends DataType {

  /** SQLを生成する場合 {@code true} */
  protected boolean generate = true;

  /** 同名のSQLファイルを上書きする場合 */
  protected boolean overwrite = true;

  /** 生成されるSQLファイルの出力先ディレクトリ */
  protected File destDir = null;

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

  public File getDestDir() {
    if (destDir == null) {
      destDir =
          new File(baseDir != null ? baseDir : getProject().getBaseDir(), "src/main/resources");
    }
    return destDir;
  }

  public void setDestDir(File destDir) {
    this.destDir = destDir;
  }

  /** @param baseDir the baseDir to set */
  protected void setBaseDir(File baseDir) {
    this.baseDir = baseDir;
  }
}
