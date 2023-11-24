package com.system.wfms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.wfms.MqttModels.BattleBotData;

public interface MqttModelService {

    public BattleBotData ConvertJsonToModel(String Payload) throws JsonProcessingException;
}
