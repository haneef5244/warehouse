package com.mynic.warehouse.obj.req.car;

import com.mynic.warehouse.entity.Car;
import lombok.Data;

@Data
public class CarDetailsReq {
    String carName;
    String carType;
    String plateNumber;

    public Car getCar() {
        Car car = new Car();
        car.setPlateNumber(this.plateNumber);
        car.setCarType(this.carType);
        car.setCarName(this.carName);
        return car;
    }
}
