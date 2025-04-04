package com.dsfyr.ecommerce.model;

import com.dsfyr.ecommerce.service.ManejadorReglasService;
import lombok.Data;

@Data
public class Usuario {
    private int id;
    private String nombre;
    private Carrito carrito;

    public Usuario() {
        this.carrito = new Carrito(); // Inicializa un carrito vacío
    }

    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.carrito = new Carrito(); // Inicializa un carrito vacío
    }

    public double getTotalCarrito() {
        return carrito.getTotal();
    }

    public void agregarItemCarrito(Producto producto, int cantidad, ManejadorReglasService manejadorReglas) {
        carrito.agregarItem(producto, cantidad, manejadorReglas);
    }

    public void reducirCantidadItemCarrito(String sku, int cantidad) {
        carrito.reducirCantidadItem(sku, cantidad);
    }

    public void eliminarItemCarrito(String sku) {
        carrito.eliminarItem(sku);
    }

    public void vaciarCarrito() {
        carrito.vaciarCarrito();
    }
}
