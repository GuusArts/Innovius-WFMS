package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BattleBotDataDetails {
    @JsonProperty("SystemInfo")
    private SystemInfo systemInfo;

    @JsonProperty("DisplaySettings")
    private DisplaySettings displaySettings;

    @JsonProperty("Kettle")
    private KettleOneWireGpioModule kettle;

    @JsonProperty("Outside")
    private OutsideSensor outside;

    @JsonProperty("Kettle Sensor")
    private KettleSensor kettleSensor;

    @JsonProperty("Kettle Setpoint")
    private KettleSetpoint kettleSetpoint;

    @JsonProperty("Kettle Actuator")
    private KettleActuator kettleActuator;

    @JsonProperty("Kettle PID")
    private KettlePID kettlePID;

    @JsonProperty("Kettle PWM")
    private KettlePWM kettlePWM;

    @JsonProperty("MPC Digital Actuator")
    private MPCDigitalActuator mpcDigitalActuator;

    public MPCDigitalActuator getMpcDigitalActuator() {
        return mpcDigitalActuator;
    }

    public void setMpcDigitalActuator(MPCDigitalActuator mpcDigitalActuator) {
        this.mpcDigitalActuator = mpcDigitalActuator;
    }


    @JsonProperty("SideSensor")
    private SideSensor sidesensor;

    public KettleOneWireGpioModule getKettle() {
        return kettle;
    }

    public void setKettle(KettleOneWireGpioModule kettle) {
        this.kettle = kettle;
    }

    public KettleActuator getKettleActuator() {
        return kettleActuator;
    }

    public void setKettleActuator(KettleActuator kettleActuator) {
        this.kettleActuator = kettleActuator;
    }

    public KettlePID getKettlePID() {
        return kettlePID;
    }

    public void setKettlePID(KettlePID kettlePID) {
        this.kettlePID = kettlePID;
    }

    public KettlePWM getKettlePWM() {
        return kettlePWM;
    }

    public void setKettlePWM(KettlePWM kettlePWM) {
        this.kettlePWM = kettlePWM;
    }


    public SideSensor getSidesensor() {
        return sidesensor;
    }

    public void setSidesensor(SideSensor sidesensor) {
        this.sidesensor = sidesensor;
    }

    public SystemInfo getSystemInfo() {
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

    public KettleOneWireGpioModule getKenel() {
        return kettle;
    }

    public void setKenel(KettleOneWireGpioModule kenel) {
        this.kettle = kenel;
    }

    public OutsideSensor getOutside() {
        return outside;
    }

    public void setOutside(OutsideSensor outside) {
        this.outside = outside;
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
}
