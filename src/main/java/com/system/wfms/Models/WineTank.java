package com.system.wfms.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Table(name = "Winetanks")
@Data
@AllArgsConstructor
@Builder
@Entity
public class WineTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double volume;

    public WineTank(){

    }
    public String getWineCategory() {
        return WineCategory;
    }

    public void setWineCategory(String wineCategory) {
        WineCategory = wineCategory;
    }

    private String WineCategory;

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
