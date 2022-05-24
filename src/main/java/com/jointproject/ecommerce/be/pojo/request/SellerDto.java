package com.jointproject.ecommerce.be.pojo.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class SellerDto {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
