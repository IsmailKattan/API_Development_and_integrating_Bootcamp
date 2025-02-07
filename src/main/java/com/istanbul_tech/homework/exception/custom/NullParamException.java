package com.istanbul_tech.homework.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class NullParamException extends BaseException{

    public NullParamException(String paramName,Throwable cause, Map<String, Object> additionalDetails, String serviceName, String path) {
        super(
                "Parameter with name: " + paramName + " is null",
                HttpStatus.BAD_REQUEST.name(),
                HttpStatus.BAD_REQUEST,
                cause,
                additionalDetails,
                serviceName,
                "Null parameter",
                path
        );
    }
}
