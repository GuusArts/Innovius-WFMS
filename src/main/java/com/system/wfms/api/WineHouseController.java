package com.system.wfms.api;


import com.system.wfms.DAL.WineRoomRepository;
import com.system.wfms.DAL.WineTankRepository;
import com.system.wfms.Models.WineRoom;
import com.system.wfms.Models.WineTank;
import com.system.wfms.service.WineTankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})

public class WineHouseController {

    @Autowired
    private final WineTankService wineTankService;

    @Autowired
    WineRoomRepository wineRoomRepository;
    @Autowired
    WineTankRepository wineTankRepository;


    public WineHouseController(WineTankService wineTankService) {
        this.wineTankService = wineTankService;
    }

    @PostMapping("/create/wineroom")
    public ResponseEntity<WineRoom> SendWinetanks(@RequestBody @Valid WineRoom wineRoom) throws Exception {
        System.out.println(wineRoom.getName());
       wineRoomRepository.save(wineRoom);
       return ResponseEntity.ok().body(wineRoom);
    }
    @GetMapping("/List/Wineroom")
    public List<WineRoom> RetrieveWineRooms() throws Exception {
        if(!wineRoomRepository.findAll().isEmpty()) {
            System.out.println(wineRoomRepository.findAll().size());
            return wineRoomRepository.findAll();
        }
            return Collections.emptyList();


    }

    @PostMapping("/create/wineTank")
    public void CreatWineTank(@RequestBody @Valid WineTank wineTank){
        System.out.println("Winetank" + wineTank.getName() + wineTank.getWineroomID() + wineTank.getWineCategory() + wineTank.getSparkID());
        wineTankRepository.save(wineTank);

    }


}
