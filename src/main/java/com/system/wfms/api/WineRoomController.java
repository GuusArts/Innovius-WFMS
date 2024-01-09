package com.system.wfms.api;


import com.system.wfms.DAL.WineRoomRepository;
import com.system.wfms.Models.WineRoom;
import com.system.wfms.service.WineTankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
@RequestMapping("/monitor")
public class WineRoomController {

    @Autowired
    private final WineTankService wineTankService;

    @Autowired
    WineRoomRepository wineRoomRepository;

    public WineRoomController(WineTankService wineTankService) {
        this.wineTankService = wineTankService;
    }

    @PostMapping("/create/wineroom")
    public ResponseEntity<WineRoom> SendWinetanks(@RequestBody @Valid WineRoom wineRoom) throws Exception {
        System.out.println(wineRoom.getName());
       wineRoomRepository.save(wineRoom);
       return ResponseEntity.ok().body(wineRoom);
    }
    @GetMapping("/winerooms")
    public ResponseEntity<List<WineRoom>> RetrieveWineRooms() throws Exception {
        if(!wineRoomRepository.findAll().isEmpty()) {
            System.out.println(wineRoomRepository.findAll().size());
            return ResponseEntity.ok().body(wineRoomRepository.findAll());
        }
            return ResponseEntity.ok(Collections.emptyList());


    }


}
