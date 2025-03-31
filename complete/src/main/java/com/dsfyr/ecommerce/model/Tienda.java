package com.dsfyr.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private List<Usuario> usuarios;
    private List<Producto> productos;

    public Tienda(List<Usuario> usuarios, List<Producto> productos) {
        this.usuarios = (usuarios != null) ? usuarios : new ArrayList<>();
        this.productos = (productos != null) ? productos : new ArrayList<>();
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Producto> getProductos() {
        return productos;
    }


    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Carrito obtenerCarrito(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario.getCarrito();
            }
        }
        throw new  Error("Usuario no encontrado");
    }
}