package com.jointproject.ecommerce.be.pojo.request;

import com.jointproject.ecommerce.be.utility.annotations.password.StrongPassword;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class LoginRequestDto {

    @Email
    @Length(min = 3, max = 40)
    private String email;

    @StrongPassword
    @Length(min = 6)
    private String password;

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
}
