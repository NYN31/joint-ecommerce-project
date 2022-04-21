package com.jointproject.ecommerce.be.service;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import com.jointproject.ecommerce.be.dto.BuyerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface BuyerService {
    public CommonResponse buyerRegistration(BuyerDto buyerDto);
}
