package com.system.wfms.service;

import com.system.wfms.Models.SideKettleSensor;
import com.system.wfms.Models.TemperatureData;
import com.system.wfms.Models.WineTank;

public interface WineTankService {
    TemperatureData processTemperatureSensor(String payload) throws Exception;

     SideKettleSensor processSideKettleSensor(String payload) throws Exception;
    TemperatureData RetrieveTempData() throws Exception;

    SideKettleSensor RetrieveSideKettleData() throws Exception;

    WineTank ConvertPayloadToWineTank(String payload) throws Exception;
    WineTank RetrieveWineTank() throws Exception;
}
