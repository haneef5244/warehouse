package com.mynic.warehouse.controller;

import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.obj.req.user.CreateUserReq;
import com.mynic.warehouse.obj.req.user.DeleteUserReq;
import com.mynic.warehouse.obj.req.user.UpdateUserReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.service.security.AuthenticationFacadeService;
import com.mynic.warehouse.service.user.CreateUserService;
import com.mynic.warehouse.service.user.DeleteUserService;
import com.mynic.warehouse.service.user.GetUserService;
import com.mynic.warehouse.service.user.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController{

    @Autowired
    CreateUserService createUserService;

    @Autowired
    DeleteUserService deleteUserService;

    @Autowired
    UpdateUserService updateUserService;

    @Autowired
    GetUserService getUserService;

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    @PostMapping(value = ResourceConstant.USER_CREATE)
    public ResponseEntity<MainResp> createUser(@RequestBody @Valid CreateUserReq req) {
        return ResponseEntity.ok(createUserService.init(req));
    }

    @PostMapping(value = ResourceConstant.USER_DELETE)
    public ResponseEntity<MainResp> deleteUser(@RequestBody @Valid DeleteUserReq req) {
        return ResponseEntity.ok(deleteUserService.init(req));
    }

    @PostMapping(value = ResourceConstant.USER_UPDATE)
    public ResponseEntity<MainResp> updateUser(@RequestBody @Valid UpdateUserReq req) {
        return ResponseEntity.ok(updateUserService.init(req));
    }

    @GetMapping(value = ResourceConstant.USER_GET)
    public ResponseEntity<MainResp> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(getUserService.init(id));
    }
}
