package org.seasar.doma.extension.gen.internal.util;

/** @author nakamura-to */
public class Pair<FIRST, SECOND> {

  private final FIRST first;

  private final SECOND second;

  /**
   * @param first
   * @param second
   */
  public Pair(FIRST first, SECOND second) {
    super();
    this.first = first;
    this.second = second;
  }

  public FIRST getFirst() {
    return first;
  }

  public SECOND getSecond() {
    return second;
  }
}
