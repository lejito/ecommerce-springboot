package com.dsfyr.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice  // Actúa como middleware para manejar errores
public class ControlGlobalErrores {

    // Captura excepciones específicas (Ejemplo: ResponseStatusException)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getReason());
        response.put("status", ex.getStatusCode().value());

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    // Captura cualquier otra excepción no manejada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error interno en el servidor");
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}