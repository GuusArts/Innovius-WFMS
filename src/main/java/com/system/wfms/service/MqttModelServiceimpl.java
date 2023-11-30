package com.system.wfms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.wfms.MqttModels.BattleBotData;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqttModelServiceimpl implements MqttModelService {

    public BattleBotData ConvertJsonToModel(String payload) throws JsonProcessingException {
        if (payload == null) {

            throw new IllegalArgumentException("Payload is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        BattleBotData battleBotData = objectMapper.readValue(payload, BattleBotData.class);
        battleBotData.setTimestamp(new Date());
        return battleBotData;
    }



}
