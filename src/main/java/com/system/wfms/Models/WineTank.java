package com.system.wfms.Models;

import javax.persistence.Entity;

@Entity
public class WineTank {
    private Long iD;
    private String name;
    private Double volume;

    public Long getiD() {
        return iD;
    }

    public void setiD(Long iD) {
        this.iD = iD;
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
