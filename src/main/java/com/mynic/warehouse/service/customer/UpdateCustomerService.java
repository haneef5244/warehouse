package com.mynic.warehouse.service.customer;

import com.mynic.warehouse.bean.CustomerBean;
import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.customer.UpdateCustomerReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.service.AbstractMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerService extends AbstractMainService {

    @Autowired
    CustomerRepository repository;

    @Override
    public MainReq process(MainReq req) {
        return req;
    }

    @Override
    public MainReq update(MainReq req) {
        UpdateCustomerReq customerReq = (UpdateCustomerReq)req;
        req.setStatus(Status.SUCCESS);
        try {
            repository.updateCustomer(customerReq.getName(), customerReq.getPhoneNumber(), customerReq.getNric());
        } catch (Exception e) {
            req.setStatus(Status.INTERNAL_SERVER_ERROR);
        }
        return req;
    }

    @Override
    public MainResp buildResponse(MainResp resp) {
        return resp;
    }
}
