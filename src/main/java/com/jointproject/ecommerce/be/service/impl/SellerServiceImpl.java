package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.Buyer;
import com.jointproject.ecommerce.be.db.entity.Seller;
import com.jointproject.ecommerce.be.db.repository.SellerRepository;
import com.jointproject.ecommerce.be.dto.SellerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.security.PasswordEncoded;
import com.jointproject.ecommerce.be.service.SellerService;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import com.jointproject.ecommerce.be.validation.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public CommonResponse sellerRegistration(SellerDto sellerDto) {
        Seller checkByEmail = sellerRepository.findByEmail(sellerDto.getEmail());
        Seller checkByUserName = sellerRepository.findByUserName(sellerDto.getUserName());

        if(Objects.nonNull(checkByEmail)){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "email already registered");
        }
        if (Objects.nonNull(checkByUserName)){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "user name already registered");
        }
        if (!new PasswordValidation().isValidPassword(sellerDto.getPassword())){
            return new CommonResponse(ResultStatus.BAD_REQUEST.getValue(), "your password does not valid");
        }
        Seller seller = new Seller();
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setUserName(sellerDto.getUserName());
        seller.setPassword(new PasswordEncoded().encoded(sellerDto.getPassword()));
        seller.setAddress(sellerDto.getAddress());
        seller.setBalance(sellerDto.getBalance());
        seller.setImage(sellerDto.getImage());
        seller.setCreatedAt(new Date());
        seller.setUpdatedAt(new Date());

        sellerRepository.save(seller);

        return new CommonResponse(ResultStatus.SUCCESS.getValue(), "Registration successful");
    }
}
