package com.jointproject.ecommerce.be.utility.annotations.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }

        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isDigit = false;

        for (char ch : password.toCharArray()) {
            isUpperCase = isUpperCase || Character.isUpperCase(ch);
            isLowerCase = isLowerCase || Character.isLowerCase(ch);
            isDigit = isDigit || Character.isDigit(ch);
        }

        return isUpperCase && isLowerCase && isDigit;
    }
}
