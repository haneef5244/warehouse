package com.mynic.warehouse.obj.req.rentalInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.entity.Car;
import com.mynic.warehouse.entity.Customer;
import com.mynic.warehouse.entity.RentalInfo;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.car.CarDetailsReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.validator.constraint.RentalInfoConstraint;
import lombok.Data;

import java.util.Date;

@RentalInfoConstraint
@Data
public class RentalInfoDetailsReq extends MainReq {
    Long customerId;
    Date startDate;
    Date endDate;
    CarDetailsReq carDetailsReq;

    public RentalInfo getRentalInfo(Customer customer, Car car) {
        RentalInfo rentalInfo = new RentalInfo();
        rentalInfo.setCharge(ResourceConstant.calculateCharge(this.getStartDate(),
                this.getEndDate(),
                customer.getResidency()));
        rentalInfo.setEndDate(this.getEndDate());
        rentalInfo.setStartDate(this.getStartDate());
        rentalInfo.setRentalStatus(ResourceConstant.RENTAL_STATUS_ACTIVE);
        rentalInfo.setCustomerRentalInfoEntities(customer);
        rentalInfo.setCar(car);
        return rentalInfo;
    }

}
