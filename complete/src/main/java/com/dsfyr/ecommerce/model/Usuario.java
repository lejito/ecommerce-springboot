package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

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

    // 🔹 Getters
    public String getNombre() { return nombre; }
    public int getId() { return id; }
    public Carrito getCarrito() { return carrito; }

    // 🔹 Setters (id no tiene setter porque es final)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public void agregarItemCarrito( Producto producto, int cantidad,ManejadorReglasService manejadorReglas) {
        carrito.agregarItem(producto, cantidad, manejadorReglas);
    }
}
