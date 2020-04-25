package com.mynic.warehouse.constant;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class ResourceConstant {
    public static final String CREATE = "/create";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String GET    = "/get";
    public static final String BY_ID  = "/{id}";
    public static final String CHARGE = "/charge";

    public static final String CUSTOMER        = "/customer";
    public static final String CUSTOMER_CREATE = ResourceConstant.CUSTOMER + ResourceConstant.CREATE;
    public static final String CUSTOMER_UPDATE = ResourceConstant.CUSTOMER + ResourceConstant.UPDATE;
    public static final String CUSTOMER_DELETE = ResourceConstant.CUSTOMER + ResourceConstant.DELETE;

    public static final String USER            = "/users";
    public static final String USER_CREATE     = ResourceConstant.USER + ResourceConstant.CREATE;
    public static final String USER_UPDATE     = ResourceConstant.USER + ResourceConstant.UPDATE;
    public static final String USER_DELETE     = ResourceConstant.USER + ResourceConstant.DELETE;
    public static final String USER_GET        = ResourceConstant.USER + ResourceConstant.GET + ResourceConstant.BY_ID;

    public static final String RENTAL_INFO     = "/rentalInfo";
    public static final String RENTAL_INFO_GET_BY_ID = ResourceConstant.RENTAL_INFO + ResourceConstant.GET + ResourceConstant.BY_ID;
    public static final String RENTAL_INFO_GET  = ResourceConstant.RENTAL_INFO + ResourceConstant.GET;
    public static final String RENTAL_INFO_CREATE = ResourceConstant.RENTAL_INFO + ResourceConstant.CREATE;
    public static final String RENTAL_INFO_CHARGE = ResourceConstant.RENTAL_INFO + ResourceConstant.GET + ResourceConstant.CHARGE;
    public static final String RENTAL_STATUS_ACTIVE = "ACTIVE";
    public static final String RENTAL_STATUS_COMPLETED = "COMPLETED";

    public static boolean isWithinRange(Date date) throws Exception {
        try {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date start = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(localDate.getYear()+"-12-01 08:00:00");
            Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(localDate.getYear()+"-12-28 08:00:00");
            return !(date.before(start) || date.after(end));
        } catch (Exception e) {
            throw e;
        }
    }

    public static Double calculateCharge(Date start, Date end, String residency) {
        Double charge = 0.00;
        try {
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(start);
            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(end);
            while (cStart.before(cEnd) || cStart.equals(cEnd)) {
                log.info("cStart=" + cStart.getTime());
                if (isWithinRange(cStart.getTime())) {
                    log.info("charging 20");
                    charge += 20.00;
                } else {
                    log.info("charging 10");
                    charge += 10.00;
                }
                cStart.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {

        }
        return discount(residency, charge);
    }

    public static Double discount(String residency, Double charge) {
        if (residency.equals("Alaska")) {
            log.info("charge="+charge);
            return charge - (charge * 0.10);
        }
        return charge;
    }
}
