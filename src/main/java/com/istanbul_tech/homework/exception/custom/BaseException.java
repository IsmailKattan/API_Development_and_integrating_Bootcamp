package com.istanbul_tech.homework.exception.custom;


import com.istanbul_tech.homework.dto.body.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class BaseException extends RuntimeException{
    // some fields
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;
    private final Map<String, Object> additionalDetails;
    private final String serviceName;
    private final String error;
    private final String path;

    // some constructors

    public BaseException(
            String message,
            String errorCode,
            HttpStatus httpStatus,
            Throwable cause,
            Map<String, Object> additionalDetails,
            String serviceName,
            String error,
            String path
    ) {
        super(message, cause);
        this.errorCode = errorCode != null ? errorCode : HttpStatus.INTERNAL_SERVER_ERROR.name();
        this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
        this.timestamp = LocalDateTime.now();
        this.additionalDetails = additionalDetails != null ? additionalDetails : Map.of();
        this.serviceName = serviceName != null ? serviceName : "UNKNOWN_SERVICE";
        this.error = error != null ? error : "UNKNOWN_ERROR";
        this.path = path != null ? path : "UNKNOWN_PATH";
    }


    public ErrorResponse toErrorResponse() {
        return ErrorResponse.builder()
                .timestamp(this.timestamp.toString())
                .httpCode(httpStatus.value())
                .httpStatus(httpStatus.getReasonPhrase())
                .message(getMessage())
                .error(error)
                .path(path)
                .build();
    }

}
