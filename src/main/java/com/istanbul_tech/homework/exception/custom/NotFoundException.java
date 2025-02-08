package com.istanbul_tech.homework.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class NotFoundException extends BaseException{
    public NotFoundException(String message, Map<String, Object> additionalDetails, String serviceName,  Throwable cause, String path) {
        super(
                message,
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND,
                cause,
                additionalDetails,
                serviceName,
                "Not found",
                path
        );
    }
}
