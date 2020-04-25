package com.mynic.warehouse;

import com.mynic.warehouse.constant.Status;
import com.mynic.warehouse.entity.Car;
import com.mynic.warehouse.entity.Customer;
import com.mynic.warehouse.entity.RentalInfo;
import com.mynic.warehouse.obj.resp.GetAllRentalInfoResp;
import com.mynic.warehouse.repository.RentalInfoRepository;
import com.mynic.warehouse.service.rentalInfo.RentalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class WarehouseApplicationTests {

    @InjectMocks
    RentalInfoService service;

    @Mock
    RentalInfoRepository rentalInfoRepository;

    @Test
    public void testGetRole() {
        GetAllRentalInfoResp resp = mock(GetAllRentalInfoResp.class);
        doNothing().when(resp).setRentalInfoResplist(any());
        RentalInfo rentalInfo = new RentalInfo();
        rentalInfo.setCar(new Car());
        rentalInfo.setCustomerRentalInfoEntities(new Customer());
        when(rentalInfoRepository.findAll()).thenReturn(Arrays.asList(rentalInfo));
        Assert.assertEquals(Status.SUCCESS.message, service.init().getMessage());
    }

}
