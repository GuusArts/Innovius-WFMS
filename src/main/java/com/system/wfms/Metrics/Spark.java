package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "sparkdata")
@Entity
public class Spark {
    @Id
    @Column(name = "SparkID")
    private Integer SparkID;
    @Column(name = "sparkName")
    private String SparkName;


    @OneToMany(mappedBy = "spark", cascade = CascadeType.ALL)
    private List<Sensor> sensors;


    public Integer getSparkID() {
        return SparkID;
    }

    public void setSparkID(Integer sparkID) {
        SparkID = sparkID;
    }


    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }




    public String getSparkName() {
        return SparkName;
    }

    public void setSparkName(String name) {
        this.SparkName = name;
    }


}
