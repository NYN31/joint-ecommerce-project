package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.BuyerEntity;
import com.jointproject.ecommerce.be.db.entity.SellerEntity;
import com.jointproject.ecommerce.be.db.repository.BuyerRepository;
import com.jointproject.ecommerce.be.db.repository.SellerRepository;
import com.jointproject.ecommerce.be.pojo.request.LoginRequestDto;
import com.jointproject.ecommerce.be.pojo.response.JwtTokenResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.service.JwtTokenService;
import com.jointproject.ecommerce.be.service.LoginService;
import com.jointproject.ecommerce.be.utility.CreateObject;
import com.jointproject.ecommerce.be.utility.enums.RoleStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    public final CreateObject createObject;
    public final BuyerRepository buyerRepository;
    public final SellerRepository sellerRepository;
    public final JwtTokenService jwtTokenService;

    public LoginServiceImpl(CreateObject createObject,
                            BuyerRepository buyerRepository,
                            SellerRepository sellerRepository,
                            JwtTokenService jwtTokenService) {
        this.createObject = createObject;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public ResultResponse loginBuyer(LoginRequestDto request, RoleStatus roleStatus) {
        BuyerEntity user = (BuyerEntity) getUser(request.getEmail(), roleStatus);

        JwtTokenResponse response = new JwtTokenResponse();
        response.setToken(jwtTokenService.jwtTokenCreation(request.getEmail()));
        return createObject.createResultResponse(response);
    }

    @Override
    public ResultResponse loginSeller(LoginRequestDto request, RoleStatus roleStatus) {
        SellerEntity user = (SellerEntity) getUser(request.getEmail(), roleStatus);

        JwtTokenResponse response = new JwtTokenResponse();
        response.setToken(jwtTokenService.jwtTokenCreation(request.getEmail()));
        return createObject.createResultResponse(response);
    }

    private Object getUser(String email, RoleStatus roleStatus) {
        if(roleStatus.equals(RoleStatus.BUYER)) {
            Optional<BuyerEntity> optional = Optional.ofNullable(buyerRepository.findByEmail(email));

            if (optional.isEmpty()) {
                log.warn("User {} does not exist", email);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not exist");
            }

            BuyerEntity user = optional.get();
            if (!user.getEnabled()) {
                log.warn("User {} is not enabled", email);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not enabled");
            }

            return user;
        }
        else if(roleStatus.equals(RoleStatus.SELLER)) {
            Optional<SellerEntity> optional = Optional.ofNullable(sellerRepository.findByEmail(email));

            if (optional.isEmpty()) {
                log.warn("User {} does not exist", email);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not exist");
            }

            SellerEntity user = optional.get();
            if (!user.getEnabled()) {
                log.warn("User {} is not enabled", email);
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not enabled");
            }

            return user;
        }
        else {
            return null;
        }
    }
}
