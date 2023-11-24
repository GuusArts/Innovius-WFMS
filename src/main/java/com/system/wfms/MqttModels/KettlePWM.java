package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KettlePWM {
    @JsonProperty("storedSetting")
    private int storedSetting;

    @JsonProperty("desiredSetting")
    private Integer desiredSetting;

    @JsonProperty("setting")
    private Integer setting;

    @JsonProperty("value")
    private Integer value;

    public int getStoredSetting() {
        return storedSetting;
    }

    public void setStoredSetting(int storedSetting) {
        this.storedSetting = storedSetting;
    }

    public Integer getDesiredSetting() {
        return desiredSetting;
    }

    public void setDesiredSetting(Integer desiredSetting) {
        this.desiredSetting = desiredSetting;
    }

    public Integer getSetting() {
        return setting;
    }

    public void setSetting(Integer setting) {
        this.setting = setting;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
