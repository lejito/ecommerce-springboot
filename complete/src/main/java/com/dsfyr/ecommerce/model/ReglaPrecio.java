package com.dsfyr.ecommerce.model;

public interface ReglaPrecio {
    boolean esAplicable(String sku);
    float calcularTotal(int cantidad, float precio);
}
