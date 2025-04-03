package com.dsfyr.ecommerce.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequestBodyReducirCantidadCarritoDTO {

    @NotNull(message = "SKU no puede ser null")
    private String sku;

    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    @NotNull(message = "Cantidad no puede ser null")
    private int cantidad;
}
