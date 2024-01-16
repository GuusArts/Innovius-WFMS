package com.system.wfms.api;


import com.system.wfms.DAL.WineRoomRepository;
import com.system.wfms.DAL.WineTankRepository;
import com.system.wfms.Models.WineRoom;
import com.system.wfms.Models.WineTank;
import com.system.wfms.service.WineTankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@CrossOrigin (origins= {"http://localhost:8080","http://localhost:5000"})
public class WineHouseController {

    @Autowired
    WineTankService wineTankService;

    @Autowired
    WineRoomRepository wineRoomRepository;
    @Autowired
    WineTankRepository wineTankRepository;




    @PostMapping("/create/wineroom")
    public WineRoom SendWinetanks(@RequestBody @Valid WineRoom wineRoom) throws Exception {
        System.out.println(wineRoom.getName());
       wineRoomRepository.save(wineRoom);
       return wineRoom;
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

    @GetMapping("/wineroom/{WineroomID}/winetanks")
    public List<WineTank> getWineTanksByWineRoomId(@PathVariable Long WineroomID) {
            System.out.println("Searching for this id " + WineroomID.toString());
            List<WineTank> wineTanks = wineTankService.getWineTanksByWineRoomId(WineroomID);

            if (wineTanks.isEmpty()) {
                System.out.println("Wineroom is empty.");
                return Collections.emptyList();
            }
            return wineTanks;
    }


}
