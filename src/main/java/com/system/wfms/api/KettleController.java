package com.system.wfms.api;


import com.system.wfms.Models.TemperatureData;
import com.system.wfms.service.TemperatureSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
@RequestMapping("/Monitor")
public class KettleController {

    private final TemperatureSensorService temperatureSensorService;

    @GetMapping("/Temp")
    public ResponseEntity<TemperatureData> SendTemp() throws Exception {
        if(!temperatureSensorService.RetrieveTempData().getTemp().isNaN()){
            System.out.println("Page called");
            return ResponseEntity.ok().body(temperatureSensorService.RetrieveTempData());

        }
        return ResponseEntity.badRequest().build();
    }

    public KettleController(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;

    }
}
