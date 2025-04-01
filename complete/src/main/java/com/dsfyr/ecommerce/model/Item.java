package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.service.ManejadorReglasService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item{
    public Producto producto;
    public int cantidad;
    private ManejadorReglasService manejadorReglas;
    public Item(Producto producto, int cantidad, ManejadorReglasService manejadorReglas) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.manejadorReglas = manejadorReglas;
    }

    public Producto getProducto() {
        return producto;
    }
     @JsonProperty("subTotal")
    public double getTotal() {
        return calcularTotal();
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public float calcularTotal(){
        ReglaPrecio reglaPrecio = manejadorReglas.obtenerRegla(producto.getSku());
        return reglaPrecio.calcularTotal(cantidad, producto.getPrecioUnitario());
    }

}