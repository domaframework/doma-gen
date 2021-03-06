package org.seasar.doma.extension.gen;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

/** @author taedium */
public class GeneratorStub extends Generator {

  private StringWriter writer = new StringWriter(300);

  @Override
  protected boolean exists(File file) {
    return false;
  }

  @Override
  protected void mkdirs(File dir) {}

  @Override
  protected Writer openWriter(GenerationContext context) {
    return writer;
  }

  protected String getResult() {
    return writer.toString();
  }

  protected void clear() {
    writer = new StringWriter(300);
  }
}
