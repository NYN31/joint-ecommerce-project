package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.dto.BuyerDto;
import com.jointproject.ecommerce.be.dto.SellerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/registration")
    public CommonResponse sellerRegistration(@RequestBody SellerDto sellerDto){
        return sellerService.sellerRegistration(sellerDto);
    }
}
