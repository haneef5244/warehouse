package com.mynic.warehouse.batch;

import com.mynic.warehouse.constant.ResourceConstant;
import com.mynic.warehouse.entity.RentalInfo;
import com.mynic.warehouse.repository.RentalInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UpdateRentalInfoStatus {

    @Autowired
    RentalInfoRepository rentalInfoRepository;

    @Transactional
    //@Scheduled(cron = "*/10 * * * * *")
    public void trackOverduePayments() {
        List<RentalInfo> rentalInfos = rentalInfoRepository.findCompletedActiveRents(new Date());
        if (!rentalInfos.isEmpty()) {
            for (RentalInfo ri : rentalInfos) {
                ri.setRentalStatus(ResourceConstant.RENTAL_STATUS_COMPLETED);
                log.info("user.name=" + ri.getCustomerRentalInfoEntities().getUser().getName());
            }
            rentalInfoRepository.saveAll(rentalInfos);
        }
    }
}