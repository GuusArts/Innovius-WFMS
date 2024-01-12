package com.system.wfms.service;

import com.system.wfms.Metrics.Spark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MetricsService   {
    public void GetSparkJson(String json);

    public void SendMetricsToDB(List<Spark> sparkList);
}
