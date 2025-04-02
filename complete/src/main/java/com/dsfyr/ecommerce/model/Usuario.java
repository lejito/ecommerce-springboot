package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.service.ManejadorReglasService;
import lombok.Data;



@Data
public class Usuario {
    private String nombre;
    private int id;
    private Carrito carrito;

    // 🔹 Constructor público
    public Usuario() {
        this.carrito = new Carrito();  // Inicializa un carrito vacío
    }
    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.carrito = new Carrito();  // Inicializa un carrito vacío
    }
    
    public void agregarItemCarrito( Producto producto, int cantidad,ManejadorReglasService manejadorReglas) {
        carrito.agregarItem(producto, cantidad, manejadorReglas);
    }
}
