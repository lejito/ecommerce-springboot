package com.dsfyr.ecommerce.model;
import com.fasterxml.jackson.annotation.JsonProperty;
public  class Producto {
    public String sku;
    public String nombre;
    public String descripcion;
    @JsonProperty("stock")  public int unidadesDisponibles;
    @JsonProperty("precio")  public  float precioUnitario;

    public  Producto() {}
    public Producto(String sku, String nombre,String descripcion, int unidadesDisponibles, float precioUnitario ) {
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadesDisponibles = unidadesDisponibles;
        this.precioUnitario = precioUnitario;
    }

    public boolean tieneUnidades(int cantidad) {
        return unidadesDisponibles >= cantidad;
    }
    public void descontarUnidades(int cantidad) {
        unidadesDisponibles -= cantidad;
    }
    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }
}