package com.istanbul_tech.homework.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UsernameInUseException extends BaseException{
    public UsernameInUseException(Throwable cause, Map<String, Object> additionalDetails, String serviceName, String path) {
        super(
                "Try another username",
                HttpStatus.CONFLICT.name(),
                HttpStatus.CONFLICT,
                cause,
                additionalDetails,
                serviceName,
                "Username not available",
                path
        );
    }
}
