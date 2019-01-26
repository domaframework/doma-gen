package org.seasar.doma.extension.gen;

import org.seasar.doma.extension.gen.internal.util.ClassUtil;

/**
 * {@link ClassDesc} のサポートクラスです。
 *
 * @author taedium
 */
public class ClassDescSupport {

  /**
   * インポート名を追加します。
   *
   * @param classDesc クラス記述
   * @param importedClass インポートされるクラス
   */
  public void addImportName(ClassDesc classDesc, Class<?> importedClass) {
    String canonicalName = importedClass.getCanonicalName();
    String packageName = ClassUtil.getPackageName(canonicalName);
    if (isImportTargetPackage(classDesc, packageName)) {
      classDesc.addImportName(canonicalName);
    }
  }

  /**
   * インポート名を追加します。
   *
   * @param classDesc クラス記述
   * @param importedClassName インポートされるクラスの名前
   */
  public void addImportName(ClassDesc classDesc, String importedClassName) {
    String packageName = ClassUtil.getPackageName(importedClassName);
    if (isImportTargetPackage(classDesc, packageName)) {
      classDesc.addImportName(importedClassName);
    }
  }

  /**
   * インポート名を追加します。
   *
   * @param classDesc クラス記述
   * @param importedClass インポートされるクラス
   */
  public void addImportName(ClassDesc classDesc, ClassConstants importedClass) {
    String packageName = importedClass.getPackageName();
    if (isImportTargetPackage(classDesc, packageName)) {
      classDesc.addImportName(importedClass.getQualifiedName());
    }
  }

  /**
   * インポート名を追加します。
   *
   * @param classDesc クラス記述
   * @param importedClass インポートされるクラス
   */
  public void addImportName(ClassDesc classDesc, EnumConstants importedClass) {
    ClassConstants classConstant = importedClass.getClassConstant();
    String packageName = classConstant.getPackageName();
    if (isImportTargetPackage(classDesc, packageName)) {
      classDesc.addImportName(importedClass.getImportName());
    }
  }

  /**
   * インポート対象のパッケージの場合 {@code true} を返します。
   *
   * @param classDesc クラス記述
   * @param importPackageName インポートするパッケージ名
   * @return インポート対象のパッケージの場合 {@code true}
   */
  protected boolean isImportTargetPackage(ClassDesc classDesc, String importPackageName) {
    if (importPackageName == null) {
      return false;
    }
    if (importPackageName.isEmpty()) {
      return false;
    }
    if (importPackageName.equals(classDesc.getPackageName())) {
      return false;
    }
    if (importPackageName.equals("java.lang")) {
      return false;
    }
    return true;
  }
}
