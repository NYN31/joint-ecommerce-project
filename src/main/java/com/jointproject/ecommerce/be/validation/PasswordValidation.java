package com.jointproject.ecommerce.be.validation;

public class PasswordValidation {
    public boolean isValidPassword(String password){
        int len = password.length();
        int lowerCase = (int) password.chars().filter((s) -> Character.isLowerCase(s)).count();
        int upperCase = (int) password.chars().filter((s) -> Character.isUpperCase(s)).count();
        int nonAlphabetic = len-(lowerCase+upperCase);

        if(len < 6 || lowerCase == 0 || upperCase == 0){
            return false;
        }
        return true;
    }
}
