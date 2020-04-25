package com.mynic.warehouse.service.user;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.user.UpdateUserReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.service.AbstractMainService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService extends AbstractMainService {

    @Autowired
    UserRepository repository;

    @Override
    public MainReq process(MainReq req) {
        return req;
    }

    @Override
    public MainReq update(MainReq req) {
        UpdateUserReq userReq = (UpdateUserReq) req;
        try {
            repository.updateUser(userReq.getName(), userReq.getUsername());
            userReq.setStatus(Status.SUCCESS);
        } catch (Exception e) {
            userReq.setStatus(Status.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return userReq;
    }

    @Override
    public MainResp buildResponse(MainResp resp) {
        return resp;
    }
}
