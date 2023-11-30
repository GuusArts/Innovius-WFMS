package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleBotDataDetails {
    @JsonProperty("SystemInfo")
    private com.system.wfms.MqttModels.SystemInfo systemInfo;

    @JsonProperty("DisplaySettings")
    private DisplaySettings displaySettings;

    @JsonProperty("kettleOneWireGpioModule")
    private KettleOneWireGpioModule kettleOneWireGpioModule;

    @JsonProperty("Kettle Sensor")
    private KettleSensor kettleSensor;

    @JsonProperty("Kettle Setpoint")
    private KettleSetpoint kettleSetpoint;


    @JsonProperty("Kettle Actuator")
    private KettleActuator kettleActuator;

    @JsonProperty("Kettle PWM")
    private KettlePWM kettlePWM;

    @JsonProperty("Kettle PID")
    private KettlePID kettlePID;

    // Default constructor
    public BattleBotDataDetails() {
    }

    public com.system.wfms.MqttModels.SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public DisplaySettings getDisplaySettings() {
        return displaySettings;
    }

    public void setDisplaySettings(DisplaySettings displaySettings) {
        this.displaySettings = displaySettings;
    }

    public KettleOneWireGpioModule getKettleOneWireGpioModule() {
        return kettleOneWireGpioModule;
    }

    public void setKettleOneWireGpioModule(KettleOneWireGpioModule kettleOneWireGpioModule) {
        this.kettleOneWireGpioModule = kettleOneWireGpioModule;
    }

    public KettleSensor getKettleSensor() {
        return kettleSensor;
    }

    public void setKettleSensor(KettleSensor kettleSensor) {
        this.kettleSensor = kettleSensor;
    }

    public KettleSetpoint getKettleSetpoint() {
        return kettleSetpoint;
    }

    public void setKettleSetpoint(KettleSetpoint kettleSetpoint) {
        this.kettleSetpoint = kettleSetpoint;
    }

    public KettleActuator getKettleActuator() {
        return kettleActuator;
    }

    public void setKettleActuator(KettleActuator kettleActuator) {
        this.kettleActuator = kettleActuator;
    }

    public KettlePWM getKettlePWM() {
        return kettlePWM;
    }

    public void setKettlePWM(KettlePWM kettlePWM) {
        this.kettlePWM = kettlePWM;
    }

    public KettlePID getKettlePID() {
        return kettlePID;
    }

    public void setKettlePID(KettlePID kettlePID) {
        this.kettlePID = kettlePID;
    }
}
