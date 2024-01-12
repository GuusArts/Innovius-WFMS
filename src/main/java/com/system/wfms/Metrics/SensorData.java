package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensorData")
public class SensorData {
    @Id
    @Column(name = "DataID")
    private Long dataID;

    @ManyToOne
    @JoinColumn(name = "SensorId")
    private Sensor sensor;

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


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }




}
