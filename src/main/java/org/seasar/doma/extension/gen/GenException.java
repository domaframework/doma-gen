package org.seasar.doma.extension.gen;

/**
 * このフレームワークでルートとなる実行時例外です。
 *
 * <p>このフレームワークで定義される実行時例外はすべてこのクラスのサブタイプとなります。
 *
 * @author taedium
 */
public class GenException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /** メッセージリソース */
  protected final MessageResource messageResource;

  /** メッセージへの引数 */
  protected final Object args;

  /**
   * インスタンスを構築します。
   *
   * @param messageResource メッセージリソース
   * @param args メッセージへの引数
   */
  public GenException(MessageResource messageResource, Object... args) {
    this(messageResource, null, args);
  }

  /**
   * この例外の原因となった {@link Throwable} を指定してインスタンスを構築します。
   *
   * @param messageResource メッセージリソース
   * @param cause 原因
   * @param args メッセージへの引数
   */
  public GenException(MessageResource messageResource, Throwable cause, Object... args) {
    super(messageResource.getMessage(args), cause);
    this.messageResource = messageResource;
    this.args = args;
  }

  /**
   * メッセージコードを返します。
   *
   * @return メッセージコード
   */
  public MessageResource getMessageResource() {
    return messageResource;
  }

  /**
   * メッセージへの引数を返します。
   *
   * @return メッセージへの引数
   */
  public Object getArgs() {
    return args;
  }
}
