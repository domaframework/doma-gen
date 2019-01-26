package org.seasar.doma.extension.gen;

import java.util.ArrayList;
import java.util.List;

/** @author nakamura-to */
public class SqlTestSuiteDesc {

  protected List<SqlTestCaseDesc> testCaseDescs = new ArrayList<>();

  public List<SqlTestCaseDesc> getTestCaseDescs() {
    return testCaseDescs;
  }

  public void addTestCaseDesc(SqlTestCaseDesc testCaseDesc) {
    testCaseDescs.add(testCaseDesc);
  }
}
