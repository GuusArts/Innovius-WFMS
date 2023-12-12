package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutsideSensor {
    @JsonProperty("value[degC]")
    private double value;
}
