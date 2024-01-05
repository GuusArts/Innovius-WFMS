package com.system.wfms.Metrics;

import java.util.List;

public class MetricEntry {
    private MetricData metric;
    private List<MetricValues> values;

    public MetricData getMetric() {
        return metric;
    }

    public List<MetricValues> getValues() {
        return values;
    }
}
