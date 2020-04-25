package com.mynic.warehouse.validator.validator;

import com.mynic.warehouse.entity.User;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.customer.CreateCustomerReq;
import com.mynic.warehouse.obj.req.customer.UpdateCustomerReq;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.validator.constraint.CustomerConstraint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@Component
public class CustomerValidator implements ConstraintValidator<CustomerConstraint, MainReq> {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(CustomerConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(MainReq req, ConstraintValidatorContext constraintValidatorContext) {
        if (req instanceof CreateCustomerReq) {

            log.info("nric="+((CreateCustomerReq) req).getNric());
            log.info("userid="+((CreateCustomerReq) req).getUserId());

            if (((CreateCustomerReq) req).getNric() == null || ((CreateCustomerReq) req).getNric().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("NRIC cannot be empty!").addConstraintViolation();
                return false;
            }
            if (((CreateCustomerReq) req).getUserId() == null || ((CreateCustomerReq) req).getUserId().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("User id cannot be empty!").addConstraintViolation();
                return false;
            }
            if (((CreateCustomerReq) req).getName() == null || ((CreateCustomerReq) req).getName().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Name cannot be empty!").addConstraintViolation();
                return false;
            }
            if (((CreateCustomerReq) req).getPhoneNumber() == null || ((CreateCustomerReq) req).getPhoneNumber().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Phone number cannot be empty!").addConstraintViolation();
                return false;
            }
            if (((CreateCustomerReq) req).getResidency() == null || ((CreateCustomerReq) req).getResidency().isEmpty()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Residency cannot be empty!").addConstraintViolation();
                return false;
            }
            if (customerRepository.findByNric(((CreateCustomerReq) req).getNric()) != null) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Account already exists!").addConstraintViolation();
                return false;
            }
            Optional<User> optionalUser = userRepository.findById(Long.parseLong(((CreateCustomerReq) req).getUserId()));
            if (!optionalUser.isPresent()) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("User doesn't exists!").addConstraintViolation();
                return false;
            }
            if (optionalUser.get().getCustomer() != null) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("User already has relationship!").addConstraintViolation();
                return false;
            }
        } else if (req instanceof UpdateCustomerReq) {
            if (customerRepository.findByNric(((UpdateCustomerReq) req).getNric())==null) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Customer does not exists!").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
