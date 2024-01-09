package com.system.wfms.DAL;

import com.system.wfms.Metrics.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
