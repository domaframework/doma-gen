package org.seasar.doma.extension.gen;

import freemarker.cache.TemplateLoader;
import freemarker.cache.URLTemplateLoader;
import java.net.URL;
import org.seasar.doma.extension.gen.internal.util.ResourceUtil;

/**
 * リソースを扱う{@link TemplateLoader}の実装クラスです。
 *
 * <p>JARファイルに含まれたリソースを扱えます。
 *
 * @author taedium
 */
public class ResourceTemplateLoader extends URLTemplateLoader {

  /** ベースとなるパス */
  protected String basePath;

  /**
   * インスタンスを構築します。
   *
   * @param basePath ベースとなるパス
   */
  public ResourceTemplateLoader(String basePath) {
    if (basePath == null) {
      throw new NullPointerException("basePath");
    }
    this.basePath = basePath;
  }

  @Override
  protected URL getURL(String name) {
    String path = basePath + "/" + name;
    return ResourceUtil.getResource(path);
  }
}
