package com.mynic.warehouse.service;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.resp.MainResp;

public abstract class AbstractMainService {

    public abstract MainReq process(MainReq req);
    public abstract MainReq update(MainReq req);
    public abstract MainResp buildResponse(MainResp resp);

    public MainResp init(MainReq req) {
        req.setStatus(Status.SUCCESS);
        req = process(req);
        if (req.getStatus() != Status.SUCCESS) {
            buildResponse(MainResp.builder()
                    .message(req.getStatus().message)
                    .status(req.getStatus().status)
                    .build());
        }
        req = update(req);
        return buildResponse(MainResp.builder()
                .message(req.getStatus().message)
                .status(req.getStatus().status)
                .build());

    }
}
