package com.github.jainsahab;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApdexTest {

  @Test
  public void shouldCalculateApdex() {
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    Apdex apdex = new Apdex(0.03, values);
    assertThat(apdex.getScore(), is(0.75));
  }
}
