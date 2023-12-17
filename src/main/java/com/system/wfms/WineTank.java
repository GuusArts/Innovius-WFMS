package com.system.wfms;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Table(name = "Winetanks")
@Data
@Builder
@Entity
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

    @Column(name = "Type")
    private String type;


    public String getType() {
        return type;
    }

    public WineTank(Long id, String name, Double volume, String wineCategory, String type) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.wineCategory = wineCategory;
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
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
