package com.mynic.warehouse.service.user;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.User;
import com.mynic.warehouse.obj.resp.GetUserResp;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {

    @Autowired
    UserRepository repository;

    public MainResp init(Long id) {
        User entity = null;
        try {
            entity = repository.findById(id).get();
        } catch (Exception e) {
            return MainResp.builder().status(Status.INTERNAL_SERVER_ERROR.status)
                    .message(Status.INTERNAL_SERVER_ERROR.message)
                    .build();
        }
        if (entity == null) {
            return MainResp.builder().status(Status.USER_NOT_FOUND.status)
                    .message(Status.USER_NOT_FOUND.message)
                    .build();
        }
        GetUserResp resp = new GetUserResp();
        resp.setId(entity.getId());
        resp.setName(entity.getName());
        resp.setUsername(entity.getUsername());
        resp.setStatus(Status.SUCCESS.status);
        resp.setMessage(Status.SUCCESS.message);
        return resp;
    }
}
