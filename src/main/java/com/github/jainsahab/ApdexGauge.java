package com.github.jainsahab;

import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Reservoir;
import com.codahale.metrics.Snapshot;

public class ApdexGauge implements Gauge<Double> {

  protected static final double DEFAULT_THRESHOLD = 0.5;

  protected double threshold;
  private Reservoir reservoir;

  public ApdexGauge() {
    threshold = DEFAULT_THRESHOLD;
    reservoir = new ExponentiallyDecayingReservoir();
  }

  public ApdexGauge(double threshold) {
    this.threshold = threshold;
    reservoir = new ExponentiallyDecayingReservoir();
  }

  public void add(long value) {
    this.reservoir.update(value);
  }

  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public Double getValue() {
    Snapshot snapshot = this.reservoir.getSnapshot();
    return new Apdex(threshold, snapshot.getValues()).getScore();
  }
}
