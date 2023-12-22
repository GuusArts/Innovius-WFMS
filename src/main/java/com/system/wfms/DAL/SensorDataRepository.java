package com.system.wfms.DAL;

import com.system.wfms.Models.WineRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SensorDataRepository extends JpaRepository<WineRoom, Long> {

}
