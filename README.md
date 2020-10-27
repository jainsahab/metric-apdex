Metric-Apdex
============
[![Build Status](https://travis-ci.org/jainsahab/metric-apdex.svg?branch=master)](https://travis-ci.com/jainsahab/metric-apdex)
[![Download](https://api.bintray.com/packages/jainsahab/metric-apdex/metric-apdex/images/download.svg) ](https://bintray.com/jainsahab/metric-apdex/metric-apdex/_latestVersion)

`metrics-apdex` is an extension to Dropwizards's [metrics](https://github.com/dropwizard/metrics) library which 
enhances the existing Gauge metric type and provide it with the capability to calculate Apdex score by converting the 
measurements into insights about user satisfaction.
 
Installation
------------

Add this to your `build.gradle`'s dependencies:

    implementation 'com.github.jainsahab:metric-apdex:1.0.0'


Usage
-----
Configure a ApdexGauge with a threshold value.

```java
     ApdexGauge apdexGauge = new ApdexGauge(0.03);  // 30 milliseconds
```  

Add the recorded measurements.

```java
    long[] values = {13, 21, 29, 17, 19, 23, 79, 49, 61, 139};
    for (long value : values) {
      apdexGauge.add(value);
    }
```

Retrieve the Apdex score.
```java
    apdexGauge.getValue(); // 0.75
```

LICENSE
-------

```LICENSE
Copyright (C) 2020 Prateek Jain

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
