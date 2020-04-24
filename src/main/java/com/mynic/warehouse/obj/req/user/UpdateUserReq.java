package com.mynic.warehouse.obj.req.user;

import com.mynic.warehouse.obj.req.MainReq;
import lombok.Data;

@Data
public class UpdateUserReq extends MainReq {
    Long id;
    String username,password, name;
}
