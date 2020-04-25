package com.mynic.warehouse.obj.req.customer;

import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.validator.constraint.CustomerConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@CustomerConstraint
@Data
public class UpdateCustomerReq extends MainReq {
    @NotEmpty(message = "Name must not be empty")
    String name;
    @NotEmpty(message = "Phone number must not be empty!")
    String phoneNumber;
    @NotEmpty(message = "NRIC must not be empty!")
    String nric;
}
