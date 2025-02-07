package com.istanbul_tech.homework.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class UsernameNotFoundException extends BaseException{
    public UsernameNotFoundException(
            Throwable cause,
            Map<String, Object> additionalDetails,
            String serviceName,
            String path
    ) {
        super(
                "Please check your username or register first",
                HttpStatus.NOT_FOUND.name(),
                HttpStatus.NOT_FOUND,
                cause,
                additionalDetails,
                serviceName,
                "Username not found",
                path
        );
    }
}
