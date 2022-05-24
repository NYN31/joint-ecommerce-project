package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.pojo.request.BuyerDto;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    private BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/registration")
    public ResultResponse buyerRegistration(@RequestBody @Valid BuyerDto buyerDto){
        return buyerService.buyerRegistration(buyerDto);
    }
}
