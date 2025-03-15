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
    public ResponseEntity<ErrorRecorddResponse> handleNotFoundException(HubSpotIntegrationError ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRecorddResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRecorddResponse> handleValidatedException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorRecorddResponse(HttpStatus.BAD_REQUEST.value(),"Error: Validation failed",errors));
    }
}
