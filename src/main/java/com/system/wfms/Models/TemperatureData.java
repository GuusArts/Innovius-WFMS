package com.system.wfms.Models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TempData")
@Data
@AllArgsConstructor
@Builder
public class TemperatureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temp", nullable = false, length = 20)
    private Double temp;

    @Column(name = "timestamp", nullable = false, length = 20)
    private Date timestamp;

    // Explicitly defined constructor
    public TemperatureData(Double temp, Date timestamp) {
        this.temp = temp;
        this.timestamp = timestamp;
    }
}
