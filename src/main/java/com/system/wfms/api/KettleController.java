package com.system.wfms.api;


import com.system.wfms.Models.TemperatureData;
import com.system.wfms.service.KettleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
@RequestMapping("/monitor")
public class KettleController {

    private final KettleService temperatureSensorService;

    @GetMapping("/temp")
    public ResponseEntity<TemperatureData> SendTemp() throws Exception {
        if(!temperatureSensorService.RetrieveTempData().getTemp().isNaN()){
            System.out.println("Page called");

            return ResponseEntity.ok().body(temperatureSensorService.RetrieveTempData());

        }
        return ResponseEntity.badRequest().build();
    }





    public KettleController(KettleService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;

    }
}
