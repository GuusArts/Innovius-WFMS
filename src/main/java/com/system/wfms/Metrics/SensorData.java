package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensorData")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dataID;

    @Column(name="SensorID")
    private Long sensorID;
    @Column(name="timestamp")
    private LocalDateTime timestamp;
    @Column(name="Value")
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    @ManyToOne
    @JoinColumn(name = "SensorId")
    private Sensor sensor;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Long getDataID() {
        return dataID;
    }

    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }

    public Long getSensorID() {
        return sensorID;
    }

    public void setSensorID(Long sensorID) {
        this.sensorID = sensorID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }




}
