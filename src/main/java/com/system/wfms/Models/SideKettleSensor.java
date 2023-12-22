package com.system.wfms.Models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@Builder
public class SideKettleSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temp", nullable = false, length = 20)
    private Double temp;

    @Column(name = "timestamp", nullable = false, length = 20)
    private Date timestamp;

    // Explicitly defined constructor
    public SideKettleSensor(Double temp, Date timestamp, Long id) {
        this.temp = temp;
        this.timestamp = timestamp;
        this.id = id;
    }
}
