package org.seasar.doma.extension.gen;

import java.io.File;
import java.util.regex.Pattern;
import junit.framework.TestCase;
import org.seasar.doma.extension.gen.internal.util.ResourceUtil;

/** @author taedium */
public class EntityPropertyClassNameResolverTest extends TestCase {

  public void testResolve2() throws Exception {
    EntityDesc entityDesc = new EntityDesc();
    entityDesc.setPackageName("aaa");
    entityDesc.setSimpleName("Bbb");

    EntityPropertyClassNameResolver resolver = new EntityPropertyClassNameResolver(null);
    resolver.patternMap.put(Pattern.compile("aaa\\.Bbb@ccc"), "String");
    assertEquals("String", resolver.resolve(entityDesc, "ccc", "Integer"));
    assertEquals("Integer", resolver.resolve(entityDesc, "ddd", "Integer"));
  }

  public void testResolve() throws Exception {
    String path = getClass().getName().replace('.', '/') + ".properties";
    File file = ResourceUtil.getResourceAsFile(path);
    EntityPropertyClassNameResolver resolver = new EntityPropertyClassNameResolver(file);

    EntityDesc entityDesc = new EntityDesc();
    entityDesc.setPackageName("aaa");
    entityDesc.setSimpleName("Bbb");
    assertEquals("java.lang.String", resolver.resolve(entityDesc, "ccc", "java.lang.Integer"));
    assertEquals("java.lang.Long", resolver.resolve(entityDesc, "ddd", "java.lang.Integer"));

    entityDesc = new EntityDesc();
    entityDesc.setPackageName("xxx");
    entityDesc.setSimpleName("Yyy");
    assertEquals("java.lang.Double", resolver.resolve(entityDesc, "ccc", "java.lang.Integer"));
  }
}
