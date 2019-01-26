package org.seasar.doma.extension.gen;

/**
 * {@link Logger} から処理を委譲されるクラスです。
 *
 * @author taedium
 */
public interface LoggerDelegate {

  /**
   * DEBUGレベルの情報を記録します。
   *
   * @param message メッセージ
   */
  void debug(String message);

  /**
   * DEBUGレベルの情報を記録します。
   *
   * @param throwable ログに関連した {@link Throwable}
   */
  void debug(Throwable throwable);

  /**
   * DEBUGレベルの情報を記録します。
   *
   * @param message メッセージ
   * @param throwable ログに関連した {@link Throwable}
   */
  void debug(String message, Throwable throwable);

  /**
   * INFOレベルの情報を記録します。
   *
   * @param message メッセージ
   */
  void info(String message);

  /**
   * INFOレベルの情報を記録します。
   *
   * @param throwable ログに関連した {@link Throwable}
   */
  void info(Throwable throwable);

  /**
   * INFOレベルの情報を記録します。
   *
   * @param message メッセージ
   * @param throwable ログに関連した {@link Throwable}
   */
  void info(String message, Throwable throwable);

  /**
   * ERRORレベルの情報を記録します。
   *
   * @param message メッセージ
   */
  void error(String message);

  /**
   * ERRORレベルの情報を記録します。
   *
   * @param throwable ログに関連した {@link Throwable}
   */
  void error(Throwable throwable);

  /**
   * ERRORレベルの情報を記録します。
   *
   * @param message メッセージ
   * @param throwable ログに関連した {@link Throwable}
   */
  void error(String message, Throwable throwable);
}
