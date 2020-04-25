package com.mynic.warehouse.controller;

import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.obj.req.rentalInfo.RentalInfoDetailsReq;
import com.mynic.warehouse.obj.resp.GetAllRentalInfoResp;
import com.mynic.warehouse.obj.resp.GetRentalInfoResp;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.obj.resp.RentalInfoChargeResp;
import com.mynic.warehouse.service.rentalInfo.RentalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class RentalInfoController {

    @Autowired
    RentalInfoService service;

    @GetMapping(value = ResourceConstant.RENTAL_INFO_GET_BY_ID)
    public ResponseEntity<GetRentalInfoResp> getRentalInfo (@PathVariable String id) {
        return ResponseEntity.ok(service.init(id));
    }

    @GetMapping(value = ResourceConstant.RENTAL_INFO_GET)
    public ResponseEntity<GetAllRentalInfoResp> getAllRentalInfo () {
        return ResponseEntity.ok(service.init());
    }

    @PostMapping(value = ResourceConstant.RENTAL_INFO_CREATE)
    public ResponseEntity<MainResp> createRentalInfo (@RequestBody @Valid RentalInfoDetailsReq req) {
        return ResponseEntity.ok(service.init((req)));
    }

    @GetMapping(value = ResourceConstant.RENTAL_INFO_CHARGE)
    public ResponseEntity<RentalInfoChargeResp> getStorageCharge() {
        return ResponseEntity.ok(service.getRentalCharge());
    }

}
