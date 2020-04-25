package com.mynic.warehouse.bean;

import com.mynic.warehouse.entity.RentalInfo;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Data
public class RentalInfoBean {
    RentalInfo rentalInfo;
}
