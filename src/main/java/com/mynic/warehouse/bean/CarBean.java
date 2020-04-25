package com.mynic.warehouse.bean;

import com.mynic.warehouse.entity.Car;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class CarBean {
    Car car;
}
