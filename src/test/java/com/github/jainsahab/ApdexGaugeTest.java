package com.github.jainsahab;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApdexGaugeTest {

  @Test
  public void shouldCalculateApdex() {
    ApdexGauge apdexGauge = new ApdexGauge(0.03);
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    for (long value : values) {
      apdexGauge.add(value);
    }
    assertThat(apdexGauge.getValue(), is(0.75));
  }

  @Test
  public void shouldCalculateGaugeWithUpdatedThreshold() {
    ApdexGauge apdexGauge = new ApdexGauge(0.03);
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    for (long value : values) {
      apdexGauge.add(value);
    }
    apdexGauge.setThreshold(0.04);
    assertThat(apdexGauge.getValue(), is(0.8));
  }

  @Test
  public void shouldCalculateGaugeWithDefaultThreshold() {
    ApdexGauge apdexGauge = new ApdexGauge();
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    for (long value : values) {
      apdexGauge.add(value);
    }
    assertThat(apdexGauge.getValue(), is(1.00));
  }
}
