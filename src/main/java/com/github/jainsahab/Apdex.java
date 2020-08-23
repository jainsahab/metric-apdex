package com.github.jainsahab;

public class Apdex {
  private int satisfied, tolerating, frustrated;
  private final double threshold;
  private final long[] values;

  public Apdex(double threshold, long[] values) {
    this.threshold = threshold;
    this.values = values;
    init();
  }

  private void init() {
    for (long value : this.values) {
      double normalizedValue = value / 1000.0;
      if (normalizedValue <= threshold) {
        satisfied++;
      } else if (normalizedValue <= 4 * threshold) {
        tolerating++;
      } else {
        frustrated++;
      }
    }
  }

  /**
   * Returns the current Apdex score.
   *
   * @return the current Apdex score.
   */
  public double getScore() {
    long total = values.length;
    if (total == 0) {
      return 0;
    }
    return ((satisfied + (tolerating / 2.0)) / total);
  }
}
