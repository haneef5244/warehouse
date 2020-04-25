package com.mynic.warehouse.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    Long id;
    String name;
    String phoneNumber;
    String nric;
    String residency;
    List<RentalInfoDTO> rentalInfoEntities;

}
