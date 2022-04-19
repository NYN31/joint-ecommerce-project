package com.jointproject.ecommerce.be.exception;

import com.jointproject.ecommerce.be.pojo.response.CommonResponse;
import com.jointproject.ecommerce.be.pojo.response.ResultResponse;
import com.jointproject.ecommerce.be.utility.enums.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultResponse commonExceptionHandler(Exception e) {
        logger.error("Common Exception handling");

        CommonResponse response = new CommonResponse();
        response.setMessage(e.getMessage());
        response.setCode(ResultStatus.INTERNAL_SERVER_ERROR.getValue());

        return createResultResponse(response, ResultStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResultResponse ApiExceptionHandler(ApiException e) {
        logger.error("Runtime exception handling");

        CommonResponse response = new CommonResponse();
        response.setMessage(e.getMessage());
        response.setCode(e.getResultStatus().getValue());

        return createResultResponse(response, e.getResultStatus());
    }

    private ResultResponse createResultResponse(CommonResponse object, ResultStatus resultStatus) {
        return ResultResponse.builder()
                .resultStatus(resultStatus)
                .data(object)
                .build();
    }
}
