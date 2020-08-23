package com.github.jainsahab;

import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Reservoir;
import com.codahale.metrics.Snapshot;

/**
 * A {@link com.codahale.metrics.Gauge} implementation which makes use of
 * {@link ExponentiallyDecayingReservoir} to record values, and calculates the Apdex score for a given threshold.
 */
public class ApdexGauge implements Gauge<Double> {

  protected static final double DEFAULT_THRESHOLD = 0.5;

  protected double threshold;
  private final Reservoir reservoir;

  public ApdexGauge() {
    threshold = DEFAULT_THRESHOLD;
    reservoir = new ExponentiallyDecayingReservoir();
  }

  public ApdexGauge(double threshold) {
    this.threshold = threshold;
    reservoir = new ExponentiallyDecayingReservoir();
  }

  /**
   * Adds a new recorded value to the reservoir.
   *
   * @param value a new recorded value
   */
  public void add(long value) {
    this.reservoir.update(value);
  }

  /**
   * Updates the threshold value of apdex gauge.
   *
   * @param threshold a new threshold value
   */
  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  /**
   * Returns the current Apdex score.
   *
   * @return the current Apdex score.
   */
  @Override
  public Double getValue() {
    Snapshot snapshot = this.reservoir.getSnapshot();
    return new Apdex(threshold, snapshot.getValues()).getScore();
  }
}
