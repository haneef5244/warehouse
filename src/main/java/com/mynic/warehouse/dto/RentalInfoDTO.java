package com.mynic.warehouse.dto;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.RentalInfo;
import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;

@Data
public class RentalInfoDTO {

    public RentalInfoDTO (RentalInfo rentalInfo) {
        this.setRentalStatus(rentalInfo.getRentalStatus());
        this.setCharge(rentalInfo.getCharge());
        this.setEndDate(rentalInfo.getEndDate());
        this.setStartDate(rentalInfo.getStartDate());
        CarDTO carDTO = new CarDTO();
        carDTO.setCarName(rentalInfo.getCar().getCarName());
        carDTO.setCarType(rentalInfo.getCar().getCarType());
        carDTO.setId(rentalInfo.getCar().getId());
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(rentalInfo.getCustomerRentalInfoEntities().getId());
        customerDTO.setName(rentalInfo.getCustomerRentalInfoEntities().getName());
        customerDTO.setNric(rentalInfo.getCustomerRentalInfoEntities().getNric());
        customerDTO.setPhoneNumber(rentalInfo.getCustomerRentalInfoEntities().getPhoneNumber());
        customerDTO.setResidency(rentalInfo.getCustomerRentalInfoEntities().getResidency());
        this.setCustomerRentalInfoEntities(customerDTO);
        this.setCar(carDTO);
    }

    Date startDate;
    Date endDate;
    String rentalStatus;
    Double charge;
    @Transient
    CarDTO car;
    @Transient
    CustomerDTO customerRentalInfoEntities;
}
