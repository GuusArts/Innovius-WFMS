package com.system.wfms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.system.wfms.DAL.WineRoomRepository;
import com.system.wfms.DAL.WineTankRepository;
import com.system.wfms.Models.SideKettleSensor;
import com.system.wfms.Models.TemperatureData;
import com.system.wfms.Models.WineRoom;
import com.system.wfms.Models.WineTank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WineTankServiceImpl implements WineTankService {

    private static final Logger logger = LoggerFactory.getLogger(WineTankServiceImpl.class);
    private final MqttModelService mqttModelService;

    private final WineTankRepository wineTankRepository;
    private final WineRoomRepository wineRoomRepository;
    private String Payload;

    @Autowired
    public WineTankServiceImpl(MqttModelService battlebotService, WineTankRepository wineRepository, WineRoomRepository wineRoomRepository) {
        this.mqttModelService = battlebotService;
        this.wineTankRepository = wineRepository;
        this.wineRoomRepository = wineRoomRepository;
    }


    public TemperatureData processTemperatureSensor(String payload) throws Exception {
        try {
            Payload = payload;
            Double temp = mqttModelService.ConvertJsonToModel(payload).getData().getKettleSensor().getValue();
            Date timestamp = mqttModelService.ConvertJsonToModel(payload).getTimestamp();
            Long id = Long.valueOf(1);
            logger.info("Water temperature processed successfully: {} {} {}", temp, timestamp, id);
            return new TemperatureData(temp, timestamp, id);
        } catch (IOException e) {

            logger.error("Error processing water temperature sensor data", e);
            throw new Exception("Error processing water temperature sensor data", e);
        }
    }

    public SideKettleSensor processSideKettleSensor(String payload) throws Exception {
        try {
            Payload = payload;
            Double temp = mqttModelService.ConvertJsonToModel(payload).getData().getSidesensor().getValue();
            Date timestamp = mqttModelService.ConvertJsonToModel(payload).getTimestamp();
            Long id = Long.valueOf(2);
            logger.info("Kettle temperature processed successfully: {} {} {}", temp, timestamp, id);
            return new SideKettleSensor(temp, timestamp, id);
        } catch (IOException e) {

            logger.error("Error processing kettle temperature sensor data", e);
            throw new Exception("Error processing kettle temperature sensor data", e);
        }
    }


    public WineTank ConvertPayloadToWineTank(String payload) throws JsonProcessingException {
        Payload = payload;
        Date timestamp = mqttModelService.ConvertJsonToModel(payload).getTimestamp();
        String name = mqttModelService.ConvertJsonToModel(payload).getKey();
        Long id = 1L;
        Double volume = 2000.0;
        String type = "Dry";
        String WineCategory = "Red";
        Double temperature = mqttModelService.ConvertJsonToModel(payload).getData().getKettleSensor().getValue();
        Boolean tempActuatorON = mqttModelService.ConvertJsonToModel(payload).getData().getKettlePID().isActive();
        WineRoom wineRoom = new WineRoom();
        WineTank wineTank = new WineTank(id,name,volume,WineCategory,type, temperature, tempActuatorON, wineRoom);


        return wineTank;
    }



    public void AddTank(){

    }
    public void GetWineTankFromDB(){
        List<WineTank> wineTankList = new ArrayList<>();
        wineTankList = wineTankRepository.findAll();
    }

    public void  AssignWineRoom(){
        WineRoom wineRoom = new WineRoom();

    }

    public WineTank RetrieveWineTank() throws JsonProcessingException {
         return ConvertPayloadToWineTank(Payload);
    }

    public SideKettleSensor RetrieveSideKettleData() throws Exception {
        return processSideKettleSensor(Payload);
    }




    public TemperatureData RetrieveTempData() throws Exception {
        return processTemperatureSensor(Payload);
    }


    public TemperatureData TempSendToDB;
}
