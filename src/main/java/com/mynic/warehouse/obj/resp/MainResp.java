package com.mynic.warehouse.obj.resp;

import com.mynic.warehouse.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainResp {
    int status;
    String message;
}
