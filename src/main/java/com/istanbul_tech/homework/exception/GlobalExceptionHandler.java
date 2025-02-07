package com.istanbul_tech.homework.exception;

import com.istanbul_tech.homework.exception.custom.NullParamException;
import com.istanbul_tech.homework.exception.custom.UsernameInUseException;
import com.istanbul_tech.homework.exception.custom.UsernameNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameInUseException.class)
    public ResponseEntity<?> handleUsernameInUseException(UsernameInUseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.toErrorResponse());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.toErrorResponse());
    }

    @ExceptionHandler(NullParamException.class)
    public ResponseEntity<?> handleNullParamException(NullParamException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.toErrorResponse());
    }
}
