package com.dsfyr.ecommerce.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RequestBodyReducirCantidadCarritoDTO {

    @NotNull(message = "SKU no puede ser null")
    private String sku;

    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    @NotNull(message = "Cantidad no puede ser null")
    private int cantidad;

    // Constructor vacío
    public RequestBodyReducirCantidadCarritoDTO() {
    }

    // Constructor con parámetros
    public RequestBodyReducirCantidadCarritoDTO(String sku, int cantidad) {
        this.sku = sku;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
