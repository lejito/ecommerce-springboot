package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.service.ManejadorReglasService;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Item{
    public Producto producto;
    public int cantidad;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ManejadorReglasService manejadorReglas;


    
     @JsonProperty("subTotal")
    public double getTotal() {
        return calcularTotal();
    }
    
    public float calcularTotal(){
        ReglaPrecio reglaPrecio = manejadorReglas.obtenerRegla(producto.getSku());
        return reglaPrecio.calcularTotal(cantidad, producto.getPrecioUnitario());
    }

}