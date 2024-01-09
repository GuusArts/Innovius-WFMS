package com.system.wfms.service;

import com.system.wfms.Metrics.Spark;

import java.util.List;

public interface MetricsService   {
    public void GetSparkJson(String json);

    public List<Spark> SendMetricsToDB();
}
