package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KettleSensor {
    @JsonProperty("value[degC]")
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
