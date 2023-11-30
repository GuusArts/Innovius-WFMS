package com.system.wfms.Models;

public class PIDData {

    private boolean enabled;


    private Double outputValue;


    private Double outputSetting;

    private boolean active;


    private Double p;


    private Double i;


    private Double d;

    private Double integral;

    private Double derivative;


    private Double integralReset;


    private boolean boilModeActive;


    private Double inputValue;


    private Double errorDelta;


    private Double inputSetting;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(Double outputValue) {
        this.outputValue = outputValue;
    }

    public Double getOutputSetting() {
        return outputSetting;
    }

    public void setOutputSetting(Double outputSetting) {
        this.outputSetting = outputSetting;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Double getI() {
        return i;
    }

    public void setI(Double i) {
        this.i = i;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Double getDerivative() {
        return derivative;
    }

    public void setDerivative(Double derivative) {
        this.derivative = derivative;
    }

    public Double getIntegralReset() {
        return integralReset;
    }

    public void setIntegralReset(Double integralReset) {
        this.integralReset = integralReset;
    }

    public boolean isBoilModeActive() {
        return boilModeActive;
    }

    public void setBoilModeActive(boolean boilModeActive) {
        this.boilModeActive = boilModeActive;
    }

    public Double getInputValue() {
        return inputValue;
    }

    public void setInputValue(Double inputValue) {
        this.inputValue = inputValue;
    }

    public Double getErrorDelta() {
        return errorDelta;
    }

    public void setErrorDelta(Double errorDelta) {
        this.errorDelta = errorDelta;
    }

    public Double getInputSetting() {
        return inputSetting;
    }

    public void setInputSetting(Double inputSetting) {
        this.inputSetting = inputSetting;
    }

    public PIDData(Double p, Double i, Double d, boolean boilModeActive) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.boilModeActive = boilModeActive;

    }
}
