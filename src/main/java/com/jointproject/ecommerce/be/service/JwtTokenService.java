package com.jointproject.ecommerce.be.service;

import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.JwtTokenResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    String jwtTokenCreation(String email);
    ResultResponse verify(String token);
}
