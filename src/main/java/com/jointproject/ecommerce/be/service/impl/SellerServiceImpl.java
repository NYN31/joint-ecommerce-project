package com.jointproject.ecommerce.be.service.impl;

import com.jointproject.ecommerce.be.db.entity.SellerEntity;
import com.jointproject.ecommerce.be.db.repository.SellerRepository;
import com.jointproject.ecommerce.be.exception.ApiException;
import com.jointproject.ecommerce.be.pojo.request.SellerDto;
import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.configuration.PasswordEncoded;
import com.jointproject.ecommerce.be.service.SellerService;
import com.jointproject.ecommerce.be.utility.CreateObject;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import com.jointproject.ecommerce.be.utility.enums.RoleStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final CreateObject createObject;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository,
                             CreateObject createObject) {
        this.sellerRepository = sellerRepository;
        this.createObject = createObject;
    }

    @Override
    public ResultResponse sellerRegistration(SellerDto sellerDto) {
        sellerValidation(sellerDto);

        SellerEntity seller = new SellerEntity();
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setUserName(sellerDto.getUserName());
        seller.setPassword(new PasswordEncoded().encoded(sellerDto.getPassword()));
        seller.setAddress(sellerDto.getAddress());
        seller.setBalance(sellerDto.getBalance());
        seller.setImage(sellerDto.getImage());
        seller.setRoleStatus(RoleStatus.SELLER);
        seller.setEnabled(true);

        sellerRepository.save(seller);
        log.info("seller added successfully: {}", seller);

        return createObject.createResultResponse(
                new CommonResponse(ResultStatus.SUCCESS.getValue(), ResultStatus.REGISTRATION_SUCCESS));
    }

    private void sellerValidation(SellerDto sellerDto) {
        SellerEntity checkByEmail = sellerRepository.findByEmail(sellerDto.getEmail());
        SellerEntity checkByUserName = sellerRepository.findByUserName(sellerDto.getUserName());

        if(Objects.nonNull(checkByEmail)){
            throw new ApiException(ResultStatus.BAD_REQUEST, ResultStatus.BAD_REQUEST.getValue());
        }
        if (Objects.nonNull(checkByUserName)){
            throw new ApiException(ResultStatus.BAD_REQUEST, ResultStatus.BAD_REQUEST.getValue());
        }
    }
}
