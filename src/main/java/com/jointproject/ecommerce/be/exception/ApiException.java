package com.jointproject.ecommerce.be.exception;

import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ApiException extends RuntimeException{
    private final String message;
    private final ResultStatus resultStatus;

    public ApiException() {
        this.message = "";
        this.resultStatus = ResultStatus.BAD_REQUEST;
    }

    public ApiException(ResultStatus resultStatus, String message) {
        this.message = message;
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
