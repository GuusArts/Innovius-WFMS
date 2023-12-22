package com.system.wfms.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "Winetanks")
@Data
@Entity
@NoArgsConstructor
public class WineTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "volume")
    private Double volume;

    @Column(name = "WineCategory")

    private String wineCategory;

    @Column(name = "variety")
    private String variety;

    @Column(name = "temperature")
    private Double temperature;


    @ManyToOne
    @JoinColumn(name="wineroom_id")
    private WineRoom wineRoom;
    @Column(name = "ActuatorOn")
    private Boolean tempActuatorON;




    public String getVariety() {
        return variety;
    }



    public WineTank(Long id, String name, Double volume, String wineCategory, String variety, Double temperature, Boolean tempActuatorON, WineRoom wineRoom) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.wineCategory = wineCategory;
        this.variety = variety;
        this.temperature = temperature;
        this.tempActuatorON = tempActuatorON;
        this.wineRoom = wineRoom;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }


    public String getWineCategory() {
        return wineCategory;
    }

    public void setWineCategory(String wineCategory) {
        this.wineCategory = wineCategory;
    }



    public Long getiD() {
        return id;
    }

    public void setiD(Long iD) {
        this.id = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
