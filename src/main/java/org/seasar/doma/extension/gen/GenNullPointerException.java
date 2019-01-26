package org.seasar.doma.extension.gen;

import org.seasar.doma.extension.gen.internal.message.Message;

/**
 * {@code null} でないことを期待されたパラメータへの引数が {@code null} の場合にスローされる例外です。
 *
 * <p>{@link NullPointerException} とは別にこの例外を定義しているのは、 {@literal Doma}のバグによる例外なのか、 {@literal
 * Doma}のAPIの事前条件を満たしていないことによる例外なのかを判別しやすくするためです。
 *
 * @author taedium
 */
public class GenNullPointerException extends GenException {

  private static final long serialVersionUID = 1L;

  /** {@code null} であるパラメータの名前 */
  protected final String parameterName;

  /**
   * インスタンスを構築します。
   *
   * @param parameterName {@code null} であるパラメータの名前
   */
  public GenNullPointerException(String parameterName) {
    super(Message.DOMAGEN0001, parameterName);
    this.parameterName = parameterName;
  }

  /**
   * {@code null} であるパラメータの名前を返します。
   *
   * @return {@code null} であるパラメータの名前
   */
  public String getParameterName() {
    return parameterName;
  }
}
