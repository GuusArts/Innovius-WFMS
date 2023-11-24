package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KettleSetpoint {
    @JsonProperty("value[degC]")
    private double value;

    @JsonProperty("desiredSetting[degC]")
    private Double desiredSetting;

    @JsonProperty("setting[degC]")
    private Double setting;

    @JsonProperty("storedSetting[degC]")
    private int storedSetting;

    @JsonProperty("valueUnfiltered[degC]")
    private double valueUnfiltered;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Double getDesiredSetting() {
        return desiredSetting;
    }

    public void setDesiredSetting(Double desiredSetting) {
        this.desiredSetting = desiredSetting;
    }

    public Double getSetting() {
        return setting;
    }

    public void setSetting(Double setting) {
        this.setting = setting;
    }

    public int getStoredSetting() {
        return storedSetting;
    }

    public void setStoredSetting(int storedSetting) {
        this.storedSetting = storedSetting;
    }

    public double getValueUnfiltered() {
        return valueUnfiltered;
    }

    public void setValueUnfiltered(double valueUnfiltered) {
        this.valueUnfiltered = valueUnfiltered;
    }
}