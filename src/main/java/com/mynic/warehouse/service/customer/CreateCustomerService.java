package com.mynic.warehouse.service.customer;

import com.mynic.warehouse.bean.CustomerBean;
import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.Customer;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.customer.CreateCustomerReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.repository.UserRepository;
import com.mynic.warehouse.service.AbstractMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateCustomerService extends AbstractMainService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerBean bean;

    @Override
    public MainReq process(MainReq req) {
        CreateCustomerReq customerReq = (CreateCustomerReq) req;
        Customer customer = new Customer();
        customer.setName(customerReq.getName());
        customer.setNric(customerReq.getNric());
        customer.setPhoneNumber(customerReq.getPhoneNumber());
        customer.setResidency(customerReq.getResidency());
        try {
            customer.setUser(userRepository.findById(Long.parseLong(((CreateCustomerReq) req).getUserId())).get());
            customer.getUser().setCustomer(customer);
        } catch (Exception e ) {
            req.setStatus(Status.INTERNAL_SERVER_ERROR);
            return req;
        }
        bean.setCustomer(customer);
        return req;
    }

    @Override
    public MainReq update(MainReq req) {
        try {
            customerRepository.saveAndFlush(bean.getCustomer());
            req.setStatus(Status.SUCCESS);
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
