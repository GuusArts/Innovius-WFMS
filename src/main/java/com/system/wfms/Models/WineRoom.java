package com.system.wfms.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "winerooms")
public class WineRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "amountOfTanks")
    private Integer amountOfTanks;

    @Column(name = "isClean")
    private Boolean isClean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfTanks() {
        return amountOfTanks;
    }

    public void setAmountOfTanks(Integer amountOfTanks) {
        this.amountOfTanks = amountOfTanks;
    }

    public Boolean getClean() {
        return isClean;
    }

    public void setClean(Boolean isClean) {
        this.isClean = isClean;
    }


}
