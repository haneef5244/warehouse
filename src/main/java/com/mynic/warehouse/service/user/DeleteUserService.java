package com.mynic.warehouse.service.user;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.obj.req.user.DeleteUserReq;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.service.AbstractMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService extends AbstractMainService {

    @Autowired
    UserRepository repository;

    @Override
    public MainReq process(MainReq req) {
        return req;
    }

    @Override
    public MainReq update(MainReq req) {
        DeleteUserReq deleteUserReq = (DeleteUserReq) req;
        try {
            repository.deleteById(Long.parseLong(deleteUserReq.getId()));
            deleteUserReq.setStatus(Status.SUCCESS);
        } catch (Exception e) {
            deleteUserReq.setStatus(Status.INTERNAL_SERVER_ERROR);
        }
        return deleteUserReq;
    }

    @Override
    public MainResp buildResponse(MainResp resp) {
        return resp;
    }

}
