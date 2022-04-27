package com.jointproject.ecommerce.be.utility;

import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateObject {

    public ResultResponse createResultResponse(Object object) {
        return ResultResponse
                .builder()
                .value(0)
                .data(object)
                .build();
    }
}
