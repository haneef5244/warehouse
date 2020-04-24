package com.mynic.warehouse.obj.req.customer;

import com.mynic.warehouse.obj.req.MainReq;
import lombok.Data;

@Data
public class UpdateCustomerReq extends MainReq {
    String name;
    String phoneNumber;
    String nric;
}
