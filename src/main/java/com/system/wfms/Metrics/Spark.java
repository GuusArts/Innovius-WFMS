package com.system.wfms.Metrics;

import java.util.List;

public class Spark {
    private Long id;
    private String name;
    private List<Sensor> sensors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spark(Long id, String name, List<Sensor> sensors) {
        this.id = id;
        this.name = name;
        this.sensors = sensors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
