package com.mynic.warehouse.validator.constraint;

import com.mynic.warehouse.validator.validator.CustomerValidator;
import com.mynic.warehouse.validator.validator.RentalInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RentalInfoValidator.class)
@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RentalInfoConstraint {

    String message() default "com.mynic.warehouse.validator.constraint.RentalInfoConstraint.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
