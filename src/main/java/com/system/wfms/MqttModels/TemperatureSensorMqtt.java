package com.system.wfms.MqttModels;

public class TemperatureSensorMqtt {
    private String key;
    private BattleBotData data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BattleBotData getData() {
        return data;
    }

    public void setData(BattleBotData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemperatureSensor{" +
                "key='" + key + '\'' +
                ", data=" + data +
                '}';
    }
}
