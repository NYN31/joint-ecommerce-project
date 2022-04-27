package com.jointproject.ecommerce.be.exception;

import com.jointproject.ecommerce.be.utility.enums.ResultStatus;

public class ApiException extends RuntimeException{
    private final Integer code;
    private final ResultStatus resultStatus;

    public ApiException() {
        this.code = 400;
        this.resultStatus = ResultStatus.BAD_REQUEST;
    }

    public ApiException(ResultStatus resultStatus, Integer code) {
        this.code = code;
        this.resultStatus = resultStatus;
    }

    public Integer getCode() {
        return code;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
