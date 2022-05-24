package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.pojo.request.LoginRequestDto;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.LoginService;
import com.jointproject.ecommerce.be.utility.enums.RoleStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    private final LoginService loginService;

    public AuthenticationController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/authentication/login-buyer")
    public ResultResponse loginBuyer(@RequestBody @Valid LoginRequestDto dto) {
        return loginService.loginBuyer(dto, RoleStatus.BUYER);
    }

    @PostMapping("/authentication/login-seller")
    public ResultResponse loginSeller(@RequestBody @Valid LoginRequestDto dto) {
        return loginService.loginSeller(dto, RoleStatus.SELLER);
    }
}
