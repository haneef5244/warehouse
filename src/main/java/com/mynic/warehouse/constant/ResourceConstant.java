package com.mynic.warehouse.constant;

import javax.annotation.Resource;

public class ResourceConstant {
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";

    public static final String CUSTOMER = "/customer";
    public static final String CUSTOMER_CREATE = ResourceConstant.CUSTOMER + ResourceConstant.CREATE;
    public static final String CUSTOMER_UPDATE = ResourceConstant.CUSTOMER + ResourceConstant.UPDATE;
    public static final String CUSTOMER_DELETE = ResourceConstant.CUSTOMER + ResourceConstant.DELETE;

}
