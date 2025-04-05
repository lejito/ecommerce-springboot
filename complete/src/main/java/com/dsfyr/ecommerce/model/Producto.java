package com.dsfyr.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public  class Producto {
    @NonNull
    private String sku;
    private String nombre;
    private String descripcion;
    @JsonProperty("stock")  private int unidadesDisponibles;
    @JsonProperty("precio")  private  float precioUnitario;

    public boolean tieneUnidades(int cantidad) {
        return unidadesDisponibles >= cantidad;
    }
    public void descontarUnidades(int cantidad) {
        unidadesDisponibles -= cantidad;
    }
}