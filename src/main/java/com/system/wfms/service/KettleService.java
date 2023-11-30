package com.system.wfms.service;

import com.system.wfms.Models.TemperatureData;

public interface KettleService {
    TemperatureData processTemperatureSensor(String payload) throws Exception;


    TemperatureData RetrieveTempData() throws Exception;
}
