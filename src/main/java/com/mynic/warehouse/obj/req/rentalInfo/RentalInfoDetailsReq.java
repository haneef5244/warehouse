package com.mynic.warehouse.obj.req.rentalInfo;

import com.mynic.warehouse.obj.req.car.CarDetailsReq;
import lombok.Data;

import java.util.Date;

@Data
public class RentalInfoDetailsReq {
    Date startDate;
    Date endDate;
    CarDetailsReq carDetailsReq;
}
