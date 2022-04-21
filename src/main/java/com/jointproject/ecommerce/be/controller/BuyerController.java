package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import com.jointproject.ecommerce.be.dto.BuyerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/registration")
    public CommonResponse buyerRegistration(@RequestBody BuyerDto buyerDto){
        return buyerService.buyerRegistration(buyerDto);
    }
}
