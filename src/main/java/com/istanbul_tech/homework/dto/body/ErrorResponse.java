package com.istanbul_tech.homework.dto.body;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String timestamp;
    private int httpCode;
    private String httpStatus;
    private String message;
    private String error;
    private String path;

    @Override
    public String toString() {
        return
                "{\"timestamp\": \"" + timestamp + "\", " +
                        "\"httpcode\": " + httpCode + ", " +
                        "\"httpstatus\": \"" + httpStatus + "\", " +
                        "\"message\": \"" + message + "\", " +
                        "\"error\": \"" + error + "\", " +
                        "\"path\": \"" + path + "\"}";

    }
}
