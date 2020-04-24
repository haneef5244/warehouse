package com.mynic.warehouse.validator.validator;

import com.mynic.warehouse.obj.req.user.CreateUserReq;
import com.mynic.warehouse.obj.req.user.DeleteUserReq;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.user.UpdateUserReq;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserValidator implements ConstraintValidator<UserConstraint, MainReq> {
    @Autowired
    UserRepository repository;

    @Override
    public void initialize(UserConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(MainReq req, ConstraintValidatorContext constraintValidatorContext) {

        if (req instanceof CreateUserReq) {
            CreateUserReq createUserReq = (CreateUserReq) req;
            if (!CollectionUtils.isEmpty(repository.findByUsername(createUserReq.getUsername()))) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Account already exists!");

                return false;
            }
        } else if (req instanceof DeleteUserReq) {
            DeleteUserReq userReq = (DeleteUserReq) req;
            if (CollectionUtils.isEmpty(repository.findByIdAndUsernameAndPassword(userReq.getId(),
                                        userReq.getUsername(),
                                        userReq.getPassword()))) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Wrong password entered!");
                return false;
            }
        } else if (req instanceof UpdateUserReq) {
            UpdateUserReq userReq = (UpdateUserReq) req;
            if (CollectionUtils.isEmpty(repository.findByIdAndUsernameAndPassword(userReq.getId(),
                    userReq.getUsername(),
                    userReq.getPassword()))) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Account details invalid!");
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
