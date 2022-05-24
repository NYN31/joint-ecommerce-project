package com.jointproject.ecommerce.be.controller;

import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/jwt")
public class JwtTokenController {

    private JwtTokenService jwtTokenService;

    @Autowired
    public JwtTokenController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/token/create/{email}")
    public ResultResponse jwtTokenCreation(@PathVariable String email) {
        return jwtTokenService.jwtTokenCreation(email);
    }

    @GetMapping("/token/verify")
    public ResultResponse jwtTokenVerify(@RequestHeader(name = "Authorization") String token) {
        log.info("Jwt Token Verify function");
        return jwtTokenService.verify(token);
    }
}
