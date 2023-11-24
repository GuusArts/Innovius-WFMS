package com.system.wfms.service;

import com.system.wfms.Models.TemperatureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class TemperatureSensorServiceImpl implements TemperatureSensorService {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureSensorServiceImpl.class);
    private final MqttModelService mqttModelService;

    private String Payload;

    public TemperatureSensorServiceImpl(MqttModelService battlebotService) {
        this.mqttModelService = battlebotService;

    }


    public TemperatureData processTemperatureSensor(String payload) throws Exception {
        try {
            Payload = payload;
            Double temp = mqttModelService.ConvertJsonToModel(payload).getData().getKettleSensor().getValue();
            Date timestamp = mqttModelService.ConvertJsonToModel(payload).getTimestamp();
            logger.info("Temperature processed successfully: {} {}", temp, timestamp);
            return new TemperatureData(temp, timestamp);
        } catch (IOException e) {

            logger.error("Error processing temperature sensor data", e);
            throw new Exception("Error processing temperature sensor data", e);
        }
    }

    public TemperatureData RetrieveTempData() throws Exception {
        return processTemperatureSensor(Payload);
    }



    public TemperatureData TempSendToDB;
}
