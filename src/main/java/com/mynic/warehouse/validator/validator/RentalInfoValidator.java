package com.mynic.warehouse.validator.validator;

import com.mynic.warehouse.entity.Car;
import com.mynic.warehouse.entity.Customer;
import com.mynic.warehouse.entity.Warehouse;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.rentalInfo.RentalInfoDetailsReq;
import com.mynic.warehouse.repository.CarRepository;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.repository.WarehouseRepository;
import com.mynic.warehouse.validator.constraint.RentalInfoConstraint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@Component
public class RentalInfoValidator implements ConstraintValidator<RentalInfoConstraint, MainReq> {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public boolean isValid(MainReq req, ConstraintValidatorContext constraintValidatorContext) {
        if (req instanceof RentalInfoDetailsReq) {
            if (((RentalInfoDetailsReq) req).getCarDetailsReq().getPlateNumber()==null || ((RentalInfoDetailsReq) req).getCarDetailsReq().getPlateNumber().isEmpty()) {
                constraintValidatorContext = setMessage(constraintValidatorContext, "Plate number most not be empty!");
                return false;
            }
            Car car = carRepository.findActiveCarRentalByPlateNumber(((RentalInfoDetailsReq) req).getCarDetailsReq().getPlateNumber());
            if (car != null) {
                constraintValidatorContext = setMessage(constraintValidatorContext, "Car already exists");
                return false;
            }

            log.info("Customer id= " + ((RentalInfoDetailsReq) req).getCustomerId());
            Optional<Customer> optionalCustomer = customerRepository.findById(((RentalInfoDetailsReq) req).getCustomerId());
            if (!optionalCustomer.isPresent()) {
                constraintValidatorContext = setMessage(constraintValidatorContext, "Customer does not exists");
                return false;
            }
            Warehouse warehouse = warehouseRepository.findAll().get(0);
            if (warehouse.getCurrentCapacity() + 1 > warehouse.getMaxCapacity()) {
                constraintValidatorContext = setMessage(constraintValidatorContext, "Warehouse car park is full");
                return false;
            }
        }
        return true;
    }

    public ConstraintValidatorContext setMessage(ConstraintValidatorContext ctx, String message) {
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return ctx;
    }
}
