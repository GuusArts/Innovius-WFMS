package com.system.wfms.Metrics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "sparkdata")
@Entity
public class Spark {
    @Id
    @Column(name = "SparkID")
    private Integer SparkID;
    @Column(name = "sparkName")
    private String SparkName;





    public Integer getSparkID() {
        return SparkID;
    }

    public void setSparkID(Integer sparkID) {
        SparkID = sparkID;
    }









    public String getSparkName() {
        return SparkName;
    }

    public void setSparkName(String name) {
        this.SparkName = name;
    }


}
