package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.BuyerEntity;
import com.jointproject.ecommerce.be.db.repository.BuyerRepository;
import com.jointproject.ecommerce.be.exception.ApiException;
import com.jointproject.ecommerce.be.pojo.request.BuyerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.configuration.PasswordEncoded;
import com.jointproject.ecommerce.be.service.BuyerService;
import com.jointproject.ecommerce.be.utility.CreateObject;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import com.jointproject.ecommerce.be.utility.enums.RoleStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final CreateObject createObject;

    @Autowired
    public BuyerServiceImpl(BuyerRepository buyerRepository,
                            CreateObject createObject){
        this.buyerRepository = buyerRepository;
        this.createObject = createObject;
    }

    @Override
    public ResultResponse buyerRegistration(BuyerDto buyerDto) {
        buyerValidation(buyerDto);

        BuyerEntity buyer = new BuyerEntity();
        buyer.setName(buyerDto.getName());
        buyer.setEmail(buyerDto.getEmail());
        buyer.setUserName(buyerDto.getUserName());
        buyer.setPassword(new PasswordEncoded().encoded(buyerDto.getPassword()));
        buyer.setAddress(buyerDto.getAddress());
        buyer.setBalance(buyerDto.getBalance());
        buyer.setImage(buyerDto.getImage());
        buyer.setRoleStatus(RoleStatus.BUYER);
        buyer.setEnabled(true);
        buyerRepository.save(buyer);
        log.info("Buyer added successfully: {}", buyer);

        return createObject.createResultResponse(
                new CommonResponse(ResultStatus.SUCCESS.getValue(), ResultStatus.REGISTRATION_SUCCESS));
    }

    private void buyerValidation(BuyerDto buyerDto) {
        BuyerEntity buyerEntityByEmail = buyerRepository.findByEmail(buyerDto.getEmail());
        BuyerEntity buyerEntityByUserName = buyerRepository.findByUserName(buyerDto.getUserName());

        if(Objects.nonNull(buyerEntityByEmail)){
            throw new ApiException(ResultStatus.EMAIL_ALREADY_EXIST, ResultStatus.EMAIL_ALREADY_EXIST.getValue());
        }
        if (Objects.nonNull(buyerEntityByUserName)){
            throw new ApiException(ResultStatus.USERNAME_ALREADY_EXIST, ResultStatus.USERNAME_ALREADY_EXIST.getValue());
        }
    }
}
