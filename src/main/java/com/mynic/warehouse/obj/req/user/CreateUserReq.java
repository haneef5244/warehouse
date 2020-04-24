package com.mynic.warehouse.obj.req.user;

import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@UserConstraint
@Data
public class CreateUserReq extends MainReq {
    @NotEmpty(message = "Username must not be empty!")
    private String username;
    @NotEmpty(message = "Password must not be empty!")
    private String password;
    @NotEmpty(message = "Name must not be empty!")
    private String name;
}
