package com.dsfyr.ecommerce.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.server.ResponseStatusException;
import com.dsfyr.ecommerce.DTO.ResponseDTO;
import com.dsfyr.ecommerce.Error.CustomError;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class ControlGlobalErrores {

   
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseDTO> handleResponseStatusException(ResponseStatusException ex) {
        ResponseDTO response = new ResponseDTO(ex.getMessage(), false, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof CustomError) {
            status = HttpStatus.BAD_REQUEST;
        }
        ResponseDTO response = new ResponseDTO(ex.getMessage(), false, status);
        return ResponseEntity.status(status).body(response);
    }
    
}