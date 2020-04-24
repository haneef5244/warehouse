package com.mynic.warehouse.validator.validator;

import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.customer.CreateCustomerReq;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.validator.constraint.CustomerConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CustomerValidator implements ConstraintValidator<CustomerConstraint, MainReq> {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void initialize(CustomerConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(MainReq req, ConstraintValidatorContext constraintValidatorContext) {
        if (req instanceof CreateCustomerReq) {
            if (customerRepository.findByNric(((CreateCustomerReq) req).getNric()) != null) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Account already exists!").addConstraintViolation();
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
