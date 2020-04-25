package com.mynic.warehouse.obj.req.user;

import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@UserConstraint
@Data
public class DeleteUserReq extends MainReq {
    @NotEmpty
    String id;
    @NotEmpty
    String username;
    @NotEmpty
    String password;
}
