package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import com.jointproject.ecommerce.be.db.repository.BuyerRepository;
import com.jointproject.ecommerce.be.dto.BuyerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.security.PasswordEncoded;
import com.jointproject.ecommerce.be.service.BuyerService;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import com.jointproject.ecommerce.be.validation.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public CommonResponse buyerRegistration(BuyerDto buyerDto) {
        Buyer checkByEmail = buyerRepository.findByEmail(buyerDto.getEmail());
        Buyer checkByUserName = buyerRepository.findByUserName(buyerDto.getUserName());

        if(Objects.nonNull(checkByEmail)){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "email already registered");
        }
        if (Objects.nonNull(checkByUserName)){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "user name already registered");
        }
        if (!new PasswordValidation().isValidPassword(buyerDto.getPassword())){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "your password does not valid");
        }
        Buyer buyer = new Buyer();
        buyer.setName(buyerDto.getName());
        buyer.setEmail(buyerDto.getEmail());
        buyer.setUserName(buyerDto.getUserName());
        buyer.setPassword(new PasswordEncoded().encoded(buyerDto.getPassword()));
        buyer.setAddress(buyerDto.getAddress());
        buyer.setBalance(buyerDto.getBalance());
        buyer.setImage(buyerDto.getImage());
        buyer.setCreatedAt(new Date());
        buyer.setUpdatedAt(new Date());

        buyerRepository.save(buyer);

        return new CommonResponse(ResultStatus.SUCCESS.getValue(), "Registration successful");
    }
}
