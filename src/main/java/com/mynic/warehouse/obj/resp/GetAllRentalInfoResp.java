package com.mynic.warehouse.obj.resp;

import com.mynic.warehouse.dto.RentalInfoDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetAllRentalInfoResp extends MainResp {
    List<RentalInfoDTO> rentalInfoResplist = new ArrayList<>();
}
