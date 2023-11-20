package com.system.wfms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PressureSensor {

    String UnitOfMeasurment = "Millibarr";


    private Long id;

    private int measurment1;

    private int measurment2;

    private Time TimeOfMeasurment;



    public String getUnitOfMeasurment() {
        return UnitOfMeasurment;
    }

    public void setUnitOfMeasurment(String unitOfMeasurment) {
        UnitOfMeasurment = unitOfMeasurment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMeasurment1() {
        return measurment1;
    }

    public void setMeasurment1(int measurment1) {
        this.measurment1 = measurment1;
    }

    public int getMeasurment2() {
        return measurment2;
    }

    public void setMeasurment2(int measurment2) {
        this.measurment2 = measurment2;
    }

    public Time getTimeOfMeasurment() {
        return TimeOfMeasurment;
    }

    public void setTimeOfMeasurment(Time timeOfMeasurment) {
        TimeOfMeasurment = timeOfMeasurment;
    }
}
