package com.system.wfms.Metrics;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensorData")
public class SensorData {
    @Id
    @Column(name = "DataID")
    private Long dataID;

    @Column(name = "SensorId")
    private Integer SensorId;

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


    public Integer getSensorId() {
        return SensorId;
    }

    public void setSensorId(Integer sensorId) {
        SensorId = sensorId;
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
