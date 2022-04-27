package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.pojo.request.SellerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @PostMapping("/registration")
    public ResultResponse sellerRegistration(@RequestBody SellerDto sellerDto){
        return sellerService.sellerRegistration(sellerDto);
    }
}
