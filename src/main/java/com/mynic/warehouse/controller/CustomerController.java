package com.mynic.warehouse.controller;

import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.obj.req.customer.CreateCustomerReq;
import com.mynic.warehouse.obj.req.customer.UpdateCustomerReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.service.customer.CreateCustomerService;
import com.mynic.warehouse.service.customer.UpdateCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    CreateCustomerService createCustomerService;

    @Autowired
    UpdateCustomerService updateCustomerService;

    @PostMapping(ResourceConstant.CUSTOMER_CREATE)
    public ResponseEntity<MainResp> customerCreate(@RequestBody @Valid CreateCustomerReq req) {
        return ResponseEntity.ok(createCustomerService.init(req));
    }

    @PostMapping(ResourceConstant.CUSTOMER_UPDATE)
    public ResponseEntity<MainResp> customerUpdate(@RequestBody @Valid UpdateCustomerReq req) {
        return ResponseEntity.ok(updateCustomerService.init(req));
    }



}
