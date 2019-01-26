package org.seasar.doma.extension.gen.internal.util;

import java.io.File;
import java.io.IOException;
import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.internal.message.Message;

/** @author taedium */
public final class FileUtil {

  public static String getCanonicalPath(File file) {
    try {
      return file.getCanonicalPath();
    } catch (IOException e) {
      throw new GenException(Message.DOMAGEN9001, e, e);
    }
  }

  public static File createJavaFile(File baseDir, String className) {
    AssertionUtil.assertNotNull(baseDir, className);
    String javaFilePath = className.replace('.', File.separatorChar) + ".java";
    return new File(baseDir, javaFilePath);
  }

  public static File createSqlDir(File baseDir, String className, String fileName) {
    AssertionUtil.assertNotNull(baseDir, className);
    File metaInfDir = new File(baseDir, "META-INF");
    File dir = new File(metaInfDir, className.replace('.', File.separatorChar));
    return new File(dir, fileName);
  }
}
