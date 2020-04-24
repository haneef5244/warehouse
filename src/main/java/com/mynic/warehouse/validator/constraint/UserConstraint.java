package com.mynic.warehouse.validator.constraint;

import com.mynic.warehouse.validator.validator.CustomerValidator;
import com.mynic.warehouse.validator.validator.UserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target( {ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface UserConstraint {

    String message() default "com.mynic.warehouse.validator.constraint.UserConstraint.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
