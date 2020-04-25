package com.mynic.warehouse.repository;

import com.mynic.warehouse.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    @Query("SELECT c FROM Car c," +
            "               RentalInfo ri " +
            "         WHERE ri.car=c.id" +
            "           AND ri.rentalStatus='ACTIVE'" +
            "           AND c.plateNumber=:plateNumber")
    Car findActiveCarRentalByPlateNumber(@Param("plateNumber") String plateNumber);
}
