package com.mynic.warehouse.obj.req.customer;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.validator.constraint.CustomerConstraint;
import lombok.Data;

@Data
@CustomerConstraint
public class CreateCustomerReq extends MainReq {
    private String name;
    private String phoneNumber;
    private String nric;
    private String residency;
    private String userId;
}
