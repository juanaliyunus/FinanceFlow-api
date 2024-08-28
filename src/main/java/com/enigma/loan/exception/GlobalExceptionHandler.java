package com.enigma.loan.exception;

import com.enigma.loan.dto.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommonResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CommonResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message(ex.getReason())
                .data(null)
                .build();
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CommonResponse<String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message(ex.getMessage())
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);


    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<String>> handleException(Exception ex) {
        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("An error occurred")
                .data(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
