package com.system.wfms.DAL;

import com.system.wfms.Models.WineTank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface WineRepository extends JpaRepository<WineTank, Long> {

}
