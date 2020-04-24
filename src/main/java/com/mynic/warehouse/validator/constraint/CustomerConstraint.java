package com.mynic.warehouse.validator.constraint;

import com.mynic.warehouse.validator.validator.CustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomerValidator.class)
@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerConstraint {

    String message() default "com.mynic.warehouse.validator.constraint.CustomerConstraint.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
