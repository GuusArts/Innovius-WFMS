package com.system.wfms.api;

import com.system.wfms.Metrics.Spark;
import com.system.wfms.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin (origins= {"http://localhost:8080"})
public class MetricController {
    @Autowired
    private final MetricsService metricsService;

    public MetricController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("List/Sparks")
    public List<Spark> GetAvaibleMetrics(){
       return metricsService.SendMetricsToDB();
    }


}
