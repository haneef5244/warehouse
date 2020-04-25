package com.mynic.warehouse.repository;

import com.mynic.warehouse.entity.RentalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RentalInfoRepository extends JpaRepository<RentalInfo, Long> {

    @Query("SELECT ri FROM RentalInfo ri" +
            "        WHERE ri.rentalStatus = 'ACTIVE'" +
            "          AND ri.endDate < :currentDate")
    public List<RentalInfo> findCompletedActiveRents(@Param("currentDate") Date currentDate);

}
