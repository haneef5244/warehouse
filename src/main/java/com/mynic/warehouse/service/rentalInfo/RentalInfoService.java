package com.mynic.warehouse.service.rentalInfo;

import com.mynic.warehouse.bean.CarBean;
import com.mynic.warehouse.bean.CustomerBean;
import com.mynic.warehouse.bean.RentalInfoBean;
import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.dto.RentalInfoDTO;
import com.mynic.warehouse.entity.RentalInfo;
import com.mynic.warehouse.entity.Warehouse;
import com.mynic.warehouse.exception.InsufficientWarehouseCapacityException;
import com.mynic.warehouse.obj.req.MainReq;
import com.mynic.warehouse.obj.req.rentalInfo.RentalInfoDetailsReq;
import com.mynic.warehouse.obj.resp.GetAllRentalInfoResp;
import com.mynic.warehouse.obj.resp.GetRentalInfoResp;
import com.mynic.warehouse.obj.resp.MainResp;
import com.mynic.warehouse.obj.resp.RentalInfoChargeResp;
import com.mynic.warehouse.repository.CustomerRepository;
import com.mynic.warehouse.repository.RentalInfoRepository;
import com.mynic.warehouse.repository.WarehouseRepository;
import com.mynic.warehouse.service.AbstractMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalInfoService extends AbstractMainService {

    @Autowired
    RentalInfoRepository rentalInfoRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CarBean carBean;

    @Autowired
    CustomerBean customerBean;

    @Autowired
    RentalInfoBean rentalInfoBean;


    public GetRentalInfoResp init(String id) {
        GetRentalInfoResp resp = new GetRentalInfoResp();
        RentalInfo rentalInfo = null;
        try {
            Optional<RentalInfo> optionalRentalInfo = rentalInfoRepository.findById(Long.parseLong(id));
            if (!optionalRentalInfo.isPresent()) {
                return (GetRentalInfoResp) buildResponse(resp, Status.RENTAL_INFO_NOT_FOUND);
            }
            rentalInfo = optionalRentalInfo.get();
        } catch (Exception e) {
            e.printStackTrace();
            return (GetRentalInfoResp) buildResponse(resp, Status.INTERNAL_SERVER_ERROR);
        }
        resp.setRentalInfoResp(new RentalInfoDTO(rentalInfo));
        return (GetRentalInfoResp) buildResponse(resp, Status.SUCCESS);
    }

    public GetAllRentalInfoResp init() {
        GetAllRentalInfoResp resp = new GetAllRentalInfoResp();
        List<RentalInfo> rentalInfos = null;
        try {
            rentalInfos = rentalInfoRepository.findAll();
            if (rentalInfos.isEmpty()) {
                return (GetAllRentalInfoResp) buildResponse(resp, Status.RENTAL_INFO_NOT_FOUND);
            }
        } catch (Exception e) {
            return (GetAllRentalInfoResp) buildResponse(resp, Status.INTERNAL_SERVER_ERROR);
        }
        resp.setRentalInfoResplist(rentalInfos.stream().map(s -> new RentalInfoDTO(s)).collect(Collectors.toList()));
        return (GetAllRentalInfoResp) buildResponse(resp, Status.SUCCESS);
    }

    public <T> MainResp buildResponse(T subResp, Status status) {
        MainResp resp = null;
        if (subResp instanceof GetAllRentalInfoResp) {
            resp = (GetAllRentalInfoResp) subResp;
        } else if (subResp instanceof GetRentalInfoResp) {
            resp = (GetRentalInfoResp) subResp;
        }
        resp.setStatus(status.status);
        resp.setMessage(status.message);
        return resp;
    }

    @Override
    public MainReq process(MainReq req) {
        RentalInfoDetailsReq rentalInfoDetailsReq = (RentalInfoDetailsReq) req;
        try {
            customerBean.setCustomer(customerRepository.findById(rentalInfoDetailsReq.getCustomerId()).get());
            carBean.setCar(rentalInfoDetailsReq.getCarDetailsReq().getCar());
            rentalInfoBean.setRentalInfo(rentalInfoDetailsReq.getRentalInfo(customerBean.getCustomer(), carBean.getCar()));
        } catch (Exception e) {
            req.setStatus(Status.INTERNAL_SERVER_ERROR);
        }
        return req;
    }

    @Override
    public MainReq update(MainReq req) {
        try {
            updateWarehouseCapacity();
        } catch (InsufficientWarehouseCapacityException e) {
            req.setStatus(Status.INSUFFICIENT_SLOT);
        } catch (Exception e) {
            req.setStatus(Status.INTERNAL_SERVER_ERROR);
        }
        return req;
    }

    @Override
    public MainResp buildResponse(MainResp resp) {
        return resp;
    }

    @Transactional
    public void saveRentalInfo(){
        rentalInfoRepository.save(rentalInfoBean.getRentalInfo());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateWarehouseCapacity(){
        Warehouse warehouse = warehouseRepository.findAll().get(0);

        if (warehouse.getCurrentCapacity() + 1 < warehouse.getMaxCapacity()) {
            warehouse.setCurrentCapacity(warehouse.getCurrentCapacity() + 1);
            try {
                warehouseRepository.save(warehouse);
                saveRentalInfo();
            } catch (Exception e) {
                throw e;
            }
            saveRentalInfo();
        } else {
            throw new InsufficientWarehouseCapacityException();
        }
    }

    public RentalInfoChargeResp getRentalCharge() {
        RentalInfoChargeResp resp = new RentalInfoChargeResp();
        try {
            resp.setCharge(rentalInfoRepository.findAll()
                    .stream().map(s -> s.getCharge())
                    .reduce(0.00, Double::sum)
            );
            resp.setStatus(Status.SUCCESS.status);
            resp.setMessage(Status.SUCCESS.message);
        } catch (Exception e) {
            resp.setStatus(Status.INTERNAL_SERVER_ERROR.status);
            resp.setMessage(Status.INTERNAL_SERVER_ERROR.message);
        }
        return resp;
    }
}
