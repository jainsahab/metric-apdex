package com.github.jainsahab;

import com.codahale.metrics.SlidingTimeWindowArrayReservoir;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MINUTES;
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

  @Test
  public void shouldCalculateApdexWithCustomReservoir() {
    ApdexGauge apdexGauge = new ApdexGauge(0.03, new SlidingTimeWindowArrayReservoir(1, MINUTES));
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    for (long value : values) {
      apdexGauge.add(value);
    }
    apdexGauge.setThreshold(0.04);
    assertThat(apdexGauge.getValue(), is(0.8));
  }
}
