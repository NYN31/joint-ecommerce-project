package com.jointproject.ecommerce.be.service;

import com.jointproject.ecommerce.be.dto.BuyerDto;
import com.jointproject.ecommerce.be.dto.SellerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
    public CommonResponse sellerRegistration(SellerDto sellerDto);
}
