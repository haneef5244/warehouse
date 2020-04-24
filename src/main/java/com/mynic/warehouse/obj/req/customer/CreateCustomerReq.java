package com.mynic.warehouse.obj.req.customer;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.rentalInfo.RentalInfoDetailsReq;
import com.mynic.warehouse.validator.constraint.CustomerConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@CustomerConstraint
@Data
public class CreateCustomerReq extends MainReq {
    @NotEmpty(message = "Name must no tbe empty!")
    String name;
    @NotEmpty(message = "Phone Number must not be empty!")
    String phoneNumber;
    @NotEmpty(message = "NRIC must not be empty")
    String nric;
    List<RentalInfoDetailsReq> rentalInfoDetailsReqList;

}
