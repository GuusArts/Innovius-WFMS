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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "WineCategory")
    private String wineCategory;

    @Column(name = "variety")
    private String variety;

    @Column(name = "SparkID")
    private Integer sparkID;

    @Column(name = "WineroomID")
    private Long WineroomID;

    public Integer getSparkID() {
        return sparkID;
    }

    public void setSparkID(Integer sparkID) {
        this.sparkID = sparkID;
    }

    public Long getWineroomID() {
        return WineroomID;
    }

    public void setWineroomID(Long wineroomID) {
        this.WineroomID = wineroomID;
    }

    public String getVariety() {
        return variety;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
