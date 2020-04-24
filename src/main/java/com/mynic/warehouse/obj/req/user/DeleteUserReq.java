package com.mynic.warehouse.obj.req.user;

import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import lombok.Data;

@UserConstraint
@Data
public class DeleteUserReq extends MainReq {
    Long id;
    String username;
    String password;
}
