package com.mynic.warehouse.obj.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserResp extends MainResp {

    private Long id;
    private String username, name;
}
