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
    public String sku;
    public String nombre;
    public String descripcion;
    @JsonProperty("stock")  public int unidadesDisponibles;
    @JsonProperty("precio")  public  float precioUnitario;

    public boolean tieneUnidades(int cantidad) {
        return unidadesDisponibles >= cantidad;
    }
    public void descontarUnidades(int cantidad) {
        unidadesDisponibles -= cantidad;
    }
}