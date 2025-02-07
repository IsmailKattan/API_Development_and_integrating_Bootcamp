package com.istanbul_tech.homework.dto.body;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private int httpCode;
    private String httpStatus;
    private String message;
    private Object data;
}
