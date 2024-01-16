package com.system.wfms.DAL;

import com.system.wfms.Models.WineTank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface WineTankRepository extends JpaRepository<WineTank, Long> {

        @Query("SELECT wt FROM WineTank wt WHERE wt.WineroomID = :wineroomID")
        List<WineTank> findByWineroomID(@Param("wineroomID") Long wineroomID);
    }


