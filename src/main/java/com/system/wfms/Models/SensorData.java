import com.system.wfms.Models.WineTank;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "SensorData")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SensorID")
    private Long sensorId;

    @ManyToOne
    @JoinColumn(name = "WinetankID")
    private WineTank winetank;

    @Column(name = "SensorType")
    private String sensorType;

    @Column(name = "SensorValue")
    private Float sensorValue;

    @Column(name = "Timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // Constructors, getters, and setters

    public SensorData() {
        // Default constructor
    }

    public SensorData(WineTank winetank, String sensorType, Float sensorValue, Date timestamp) {
        this.winetank = winetank;
        this.sensorType = sensorType;
        this.sensorValue = sensorValue;
        this.timestamp = timestamp;
    }

    // Getters and setters
    // ...

    // toString method (for debugging and logging)
    // ...
}
