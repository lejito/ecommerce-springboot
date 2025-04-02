package com.dsfyr.ecommerce.DTO;
import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Data
public class ResponseDTO {
    private String message ;
    private boolean success;
    private Object data;
    private HttpStatus statusCode;

    public ResponseDTO(String message, boolean success, HttpStatus statusCode) {
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }
}
