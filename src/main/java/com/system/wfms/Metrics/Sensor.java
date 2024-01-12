package com.system.wfms.Metrics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "sensors")
@Entity
public class Sensor {

    @Id
    @Column(name = "id")
    private Integer sensorID;

    @Column(name = "SparkID")
    private Integer SparkId;



    @Column(name = "SensorName")
    private String sensorName;

    @Column(name = "Unit_of_Measurement")
    private String unit_of_Measurment;

    public Integer getSparkId() {
        return SparkId;
    }

    public void setSparkId(Integer spark) {
        this.SparkId = spark;
    }
    public Integer getSensorID() {
        return sensorID;
    }

    public void setSensorID(Integer sensorID) {
        this.sensorID = sensorID;
    }


    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String name) {
        this.sensorName = name;
    }

    public String getUnit_of_Measurment() {
        return unit_of_Measurment;
    }

    public void setUnit_of_Measurment(String unit_of_Measurment) {
        this.unit_of_Measurment = unit_of_Measurment;
    }


}
