package org.seasar.doma.extension.gen.task;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.seasar.doma.extension.gen.LoggerDelegate;

/**
 * {@link Task} のログ機能を使用する {@link LoggerDelegate} の実装です。
 *
 * @author taedium
 */
public class TaskLoggerDelegate implements LoggerDelegate {

  protected final Task task;

  /**
   * インスタンスを生成します。
   *
   * @param task Antタスク
   */
  public TaskLoggerDelegate(Task task) {
    if (task == null) {
      throw new NullPointerException("task");
    }
    this.task = task;
  }

  @Override
  public void debug(String message) {
    task.log(message, Project.MSG_DEBUG);
  }

  @Override
  public void debug(Throwable throwable) {
    task.log(throwable, Project.MSG_DEBUG);
  }

  @Override
  public void debug(String message, Throwable throwable) {
    task.log(message, throwable, Project.MSG_DEBUG);
  }

  @Override
  public void info(String message) {
    task.log(message, Project.MSG_INFO);
  }

  @Override
  public void info(Throwable throwable) {
    task.log(throwable, Project.MSG_INFO);
  }

  @Override
  public void info(String message, Throwable throwable) {
    task.log(message, throwable, Project.MSG_INFO);
  }

  @Override
  public void error(String message) {
    task.log(message, Project.MSG_ERR);
  }

  @Override
  public void error(Throwable throwable) {
    task.log(throwable, Project.MSG_ERR);
  }

  @Override
  public void error(String message, Throwable throwable) {
    task.log(message, throwable, Project.MSG_ERR);
  }
}
