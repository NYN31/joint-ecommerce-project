package com.jointproject.ecommerce.be.utility.enums;

public enum ResultStatus {
    SUCCESS(200),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),


    TOKEN_VALID(1000),
    TOKEN_INVALID(1001),
    TOKEN_EXPIRED(1002);

    private final Integer value;

    ResultStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
