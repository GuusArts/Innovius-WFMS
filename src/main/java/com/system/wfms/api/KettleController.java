package com.system.wfms.api;


import com.system.wfms.DAL.WineTankRepository;
import com.system.wfms.Models.SideKettleSensor;
import com.system.wfms.Models.TemperatureData;
import com.system.wfms.Models.WineTank;
import com.system.wfms.service.WineTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
@RequestMapping("/monitor")
public class KettleController {

    @Autowired
    private final WineTankService temperatureSensorService;
    @Autowired
    WineTankRepository wineTankRepository;



    @GetMapping("/temp")
    public ResponseEntity<TemperatureData> SendTemp() throws Exception {
        if(!temperatureSensorService.RetrieveTempData().getTemp().isNaN()){
            System.out.println("Page 1 called");

            return ResponseEntity.ok().body(temperatureSensorService.RetrieveTempData());

        }
        return ResponseEntity.badRequest().build();
    }





    @GetMapping("/kettle")
    public ResponseEntity<SideKettleSensor> SendSideKettleTemp() throws Exception {
        if(!temperatureSensorService.RetrieveTempData().getTemp().isNaN()){
            System.out.println("Page 2 called");

            return ResponseEntity.ok().body(temperatureSensorService.RetrieveSideKettleData());

        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/winetank/list")
    public List<WineTank> SendWinetanks() throws Exception {
            if(!wineTankRepository.findAll().isEmpty()) {
              return wineTankRepository.findAll();
            }else {
                return Collections.emptyList();
            }

    }





    public KettleController(WineTankService temperatureSensorService, WineTankRepository wineTankRepository) {
        this.temperatureSensorService = temperatureSensorService;
        this.wineTankRepository = wineTankRepository;

    }
}
