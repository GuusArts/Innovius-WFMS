package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KettleActuator {
    @JsonProperty("storedState")
    private int storedState;

    @JsonProperty("desiredState")
    private int desiredState;

    @JsonProperty("state")
    private int state;

    public int getStoredState() {
        return storedState;
    }

    public void setStoredState(int storedState) {
        this.storedState = storedState;
    }

    public int getDesiredState() {
        return desiredState;
    }

    public void setDesiredState(int desiredState) {
        this.desiredState = desiredState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}