package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "sparkdata")
@Entity
public class Spark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SparkID")
    private Long SparkID;
    @Column(name = "sparkName")
    private String SparkName;
    @Column(name = "SensorID")
    private Long SensorID;

    public Long getSensorID() {
        return SensorID;
    }

    public void setSensorID(Long sensorID) {
        SensorID = sensorID;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    @OneToMany(mappedBy = "spark")
    private List<Sensor> sensors;

    public Long getSparkID() {
        return SparkID;
    }

    public void setSparkID(Long id) {
        this.SparkID = id;
    }


    public String getSparkName() {
        return SparkName;
    }

    public void setSparkName(String name) {
        this.SparkName = name;
    }


}
