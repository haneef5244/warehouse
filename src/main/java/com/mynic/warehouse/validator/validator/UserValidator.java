package com.mynic.warehouse.validator.validator;

import com.mynic.warehouse.constant.RoleType;
import com.mynic.warehouse.entity.User;
import com.mynic.warehouse.obj.req.user.CreateUserReq;
import com.mynic.warehouse.obj.req.user.DeleteUserReq;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.user.UpdateUserReq;
import com.mynic.warehouse.repository.RoleRepository;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@Component
public class UserValidator implements ConstraintValidator<UserConstraint, MainReq> {
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

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
            } else if (CollectionUtils.isEmpty(((CreateUserReq) req).getRoles())) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Invalid role!");
                return false;
            }
            for (String role : ((CreateUserReq) req).getRoles()) {
                if (!role.equals("ADMIN") &&
                        !role.equals("USER")) {
                    constraintValidatorContext = setMessage(constraintValidatorContext,"Invalid role!");
                    return false;
                }
            }
        } else if (req instanceof DeleteUserReq) {
            DeleteUserReq userReq = (DeleteUserReq) req;
            Optional<User> optionalUser = repository.findById(Long.parseLong(userReq.getId()));
            if (!optionalUser.isPresent()) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Account doesn't exists!");
                return false;
            }
            User user = optionalUser.get();
            if (!BCrypt.checkpw(((DeleteUserReq) req).getPassword(),user.getPassword()) ||
            !user.getUsername().equals(((DeleteUserReq) req).getUsername())) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Wrong details entered!");
                return false;
            }
        } else if (req instanceof UpdateUserReq) {
            UpdateUserReq userReq = (UpdateUserReq) req;
            Optional<User> optionalUser = repository.findById(userReq.getId());
            if (!optionalUser.isPresent()) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Account not found!");
                return false;
            }
            User user = optionalUser.get();
            if (!BCrypt.checkpw(userReq.getPassword(), user.getPassword())
            || !userReq.getUsername().equals(user.getUsername())) {
                constraintValidatorContext = setMessage(constraintValidatorContext,"Invalid account details!");
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

    public String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
