package com.mynic.warehouse.obj.req.user;

import com.mynic.warehouse.obj.req.MainReq;
import lombok.Data;

@Data
public class CustomerReq extends MainReq {
    private Long id;
    private String name;
}
