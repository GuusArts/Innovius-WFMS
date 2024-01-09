package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "sensors")
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SensorId")
    private Long sensorID;

    @OneToMany(mappedBy = "sensor")
    private List<SensorData> sensorDataList;

    @ManyToOne
    @JoinColumn(name = "SparkID")
    private Spark spark;

    @Column(name = "name")
    private String sensorName;

    @Column(name = "Unit_of_Measurement")
    private String unit_of_Measurment;


    public Long getSensorID() {
        return sensorID;
    }

    public void setSensorID(Long sensorID) {
        this.sensorID = sensorID;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    public Spark getSpark() {
        return spark;
    }

    public void setSpark(Spark spark) {
        this.spark = spark;
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
