package org.seasar.doma.extension.gen.internal.util;

import java.io.File;
import junit.framework.TestCase;

/** @author taedium */
public class FileUtilTest extends TestCase {

  public void testCreateJavaFile() throws Exception {
    File file = FileUtil.createJavaFile(new File("."), "aaa.bbb.Ccc");
    assertEquals("Ccc.java", file.getName());
  }
}
