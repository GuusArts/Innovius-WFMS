package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MPCDigitalActuator {

    @JsonProperty("storedState")
    private Integer storedState;

    @JsonProperty("desiredState")
    private Integer desiredState;

    @JsonProperty("state")
    private Integer state;


    public MPCDigitalActuator() {
    }


    public MPCDigitalActuator(Integer storedState, Integer desiredState, Integer state) {
        this.storedState = storedState;
        this.desiredState = desiredState;
        this.state = state;
    }



    public Integer getStoredState() {
        return storedState;
    }

    public void setStoredState(Integer storedState) {
        this.storedState = storedState;
    }

    public Integer getDesiredState() {
        return desiredState;
    }

    public void setDesiredState(Integer desiredState) {
        this.desiredState = desiredState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
