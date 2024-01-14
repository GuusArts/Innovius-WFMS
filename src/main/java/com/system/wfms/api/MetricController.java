package com.system.wfms.api;

import com.system.wfms.DAL.SparkRepository;
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

    @Autowired
    private final SparkRepository sparkRepository;

    public MetricController(MetricsService metricsService, SparkRepository sparkRepository) {
        this.metricsService = metricsService;
        this.sparkRepository = sparkRepository;
    }

    @GetMapping("List/Sparks")
    public List<Spark> GetAvaibleMetrics(){
        System.out.println(sparkRepository.findAll().size());
       return sparkRepository.findAll();
    }


}
