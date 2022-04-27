package com.jointproject.ecommerce.be.pojo.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Data
@Getter
@Setter
public class BuyerDto {
    @NotNull
    private String name;

    @NotNull
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String image;

    @NotNull
    private String address;

    @NotNull
    @Min(0)
    private Double balance;
}
