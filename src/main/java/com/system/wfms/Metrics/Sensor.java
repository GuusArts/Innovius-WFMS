package com.system.wfms.Metrics;

import com.google.gson.JsonArray;

public class Sensor {
    private String name;
    private String unit_of_Measurment;
    private JsonArray value;

    public Sensor(String name, String unit_of_Measurment, JsonArray value) {
        this.name = name;
        this.unit_of_Measurment = unit_of_Measurment;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit_of_Measurment() {
        return unit_of_Measurment;
    }

    public void setUnit_of_Measurment(String unit_of_Measurment) {
        this.unit_of_Measurment = unit_of_Measurment;
    }

    public JsonArray getValue() {
        return value;
    }

    public void setValue(JsonArray value) {
        this.value = value;
    }
}
