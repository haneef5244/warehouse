package com.mynic.warehouse.service.customer;

import com.mynic.warehouse.bean.CustomerBean;
import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.Car;
import com.mynic.warehouse.entity.Customer;
import com.mynic.warehouse.entity.RentalInfo;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.customer.CreateCustomerReq;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.service.AbstractMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CreateCustomerService extends AbstractMainService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerBean bean;


    @Override
    public MainReq process(MainReq req) {
        CreateCustomerReq customerReq = (CreateCustomerReq) req;
        Customer customer = new Customer();
        customer.setName(customerReq.getName());
        customer.setNric(customer.getNric());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setRentalInfoEntities(((CreateCustomerReq) req).getRentalInfoDetailsReqList().stream().map(s -> {
            Car car = new Car();
            car.setCarName(s.getCarDetailsReq().getCarName());
            car.setCarType(s.getCarDetailsReq().getCarType());
            RentalInfo rentalInfo = new RentalInfo();
            rentalInfo.setEndDate(s.getEndDate());
            rentalInfo.setStartDate(s.getStartDate());
            rentalInfo.setCar(car);
            rentalInfo.setCharge(discount(customer.getResidency(), calculateCharge(rentalInfo.getStartDate(), rentalInfo.getEndDate())));
            car.setRentalInfo(rentalInfo);
            return rentalInfo;
        }).collect(Collectors.toList()));
        bean.setCustomer(customer);

        return req;
    }

    

    @Override
    public MainReq update(MainReq req) {
        try {
            customerRepository.save(bean.getCustomer());
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

    boolean isWithinRange(Date date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date start = sdf.parse("01/12/2020");
            Date end = sdf.parse("28/12/2020");
            return !(date.before(start) || date.after(end));
        } catch (Exception e) {
            throw e;
        }
    }

    public Double calculateCharge(Date start, Date end) {
        Double charge = 0.00;
        try {
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(start);
            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(end);
            while (cStart.before(cEnd)) {
                cStart.add(Calendar.DAY_OF_MONTH, 1);
                if (isWithinRange(cStart.getTime())) {
                    charge += 20.00;
                } else {
                    charge += 10.00;
                }
            }
        } catch (Exception e) {

        }
        return charge;
    }

    public Double discount(String residency, Double charge) {
        if (residency.equalsIgnoreCase("Alaska")) {
            return charge - (charge * 0.10);
        }
        return charge;
    }

}
