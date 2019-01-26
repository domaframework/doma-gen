package org.seasar.doma.extension.gen;

/**
 * {@link LoggerDelegate} の何もしない実装です。
 *
 * @author taedium
 */
public class NullLoggerDelegate implements LoggerDelegate {

  @Override
  public void debug(String message) {}

  @Override
  public void debug(Throwable throwable) {}

  @Override
  public void debug(String message, Throwable throwable) {}

  @Override
  public void info(String message) {}

  @Override
  public void info(Throwable throwable) {}

  @Override
  public void info(String message, Throwable throwable) {}

  @Override
  public void error(String message) {}

  @Override
  public void error(Throwable throwable) {}

  @Override
  public void error(String message, Throwable throwable) {}
}
