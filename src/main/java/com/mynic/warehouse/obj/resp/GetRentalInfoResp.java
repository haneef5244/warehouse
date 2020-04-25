package com.mynic.warehouse.obj.resp;

import com.mynic.warehouse.dto.RentalInfoDTO;
import lombok.Data;

import java.util.Date;

@Data
public class GetRentalInfoResp extends MainResp{
    RentalInfoDTO rentalInfoResp;

}
