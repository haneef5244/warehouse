package com.mynic.warehouse.obj.req;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.validator.constraint.UserConstraint;
import lombok.Data;

@UserConstraint
@Data
public class MainReq {
    Status status;
}
