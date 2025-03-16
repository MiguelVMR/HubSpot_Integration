package com.integracao.hubspot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class GlobalExceptionHandler
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 19/11/2024
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HubSpotIntegrationError.class)
    public ResponseEntity<ErrorRecorddResponse> handleIntegrationException(HubSpotIntegrationError ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRecorddResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorRecorddResponse> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorRecorddResponse(HttpStatus.FOUND.value(),ex.getMessage()));
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorRecorddResponse> handleUserException(UserException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorRecorddResponse(HttpStatus.CONFLICT.value(),ex.getMessage()));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorRecorddResponse> handleLoginException(LoginException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorRecorddResponse(HttpStatus.UNAUTHORIZED.value(),ex.getMessage()));
    }
}
