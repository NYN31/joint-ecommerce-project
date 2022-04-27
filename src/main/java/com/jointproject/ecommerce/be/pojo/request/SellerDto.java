package com.jointproject.ecommerce.be.pojo.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SellerDto {
    private String name;
    private String userName;
    private String email;
    private String password;
    private String image;
    private String address;
    private Double balance;
}