package com.jointproject.ecommerce.be.service;

import com.jointproject.ecommerce.be.pojo.request.LoginRequestDto;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.utility.enums.RoleStatus;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    ResultResponse loginBuyer(LoginRequestDto request, RoleStatus roleStatus);
    ResultResponse loginSeller(LoginRequestDto request, RoleStatus roleStatus);
}
