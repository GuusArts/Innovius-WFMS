package com.system.wfms.MqttModels;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SystemInfo {

    @JsonProperty("updatesPerSecond")
    private double updatesPerSecond;

    @JsonProperty("voltage5")
    private double voltage5;

    @JsonProperty("voltageExternal")
    private double voltageExternal;

    @JsonProperty("memoryFree")
    private int memoryFree;

    @JsonProperty("memoryFreeContiguous")
    private int memoryFreeContiguous;

    @JsonProperty("memoryFreeLowest")
    private int memoryFreeLowest;

    @JsonProperty("uptime[second]")
    private double uptime;

    public double getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    public void setUpdatesPerSecond(double updatesPerSecond) {
        this.updatesPerSecond = updatesPerSecond;
    }

    public double getVoltage5() {
        return voltage5;
    }

    public void setVoltage5(double voltage5) {
        this.voltage5 = voltage5;
    }

    public double getVoltageExternal() {
        return voltageExternal;
    }

    public void setVoltageExternal(double voltageExternal) {
        this.voltageExternal = voltageExternal;
    }

    public int getMemoryFree() {
        return memoryFree;
    }

    public void setMemoryFree(int memoryFree) {
        this.memoryFree = memoryFree;
    }

    public int getMemoryFreeContiguous() {
        return memoryFreeContiguous;
    }

    public void setMemoryFreeContiguous(int memoryFreeContiguous) {
        this.memoryFreeContiguous = memoryFreeContiguous;
    }

    public int getMemoryFreeLowest() {
        return memoryFreeLowest;
    }

    public void setMemoryFreeLowest(int memoryFreeLowest) {
        this.memoryFreeLowest = memoryFreeLowest;
    }

    public double getUptime() {
        return uptime;
    }

    public void setUptime(double uptime) {
        this.uptime = uptime;
    }
}