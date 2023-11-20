package com.system.wfms.api;


import com.system.wfms.Sensors;
import com.system.wfms.TemperatureSensor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
@RequestMapping("/Control")
public class KettleController {



    @PostMapping("/Temp")
    public void GetTempView(@RequestBody @Valid TemperatureSensor TempSensor) {
        System.out.println(TempSensor);
    }

}
