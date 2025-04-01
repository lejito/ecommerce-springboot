package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.Error.ProductoNoEncontrado;
import com.dsfyr.ecommerce.Error.UsuarioNoEncontrado;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

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

    public Carrito agregarItemCarrito(int idUsuario, String sku, int cantidad, ManejadorReglasService manejadorReglas) {

        Usuario usuario = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst().orElse(null);
        if(usuario == null){
            throw new UsuarioNoEncontrado(String.format("Usuario con ID %d no encontrado", idUsuario));
        }
        Producto producto = productos.stream().filter(p -> p.getSku().equals(sku)).findFirst().orElse(null);
        if (producto == null) {
            throw new ProductoNoEncontrado(String.format("Producto con SKU %s no encontrado", sku));
        }
        usuario.agregarItemCarrito(producto, cantidad, manejadorReglas);
        return usuario.getCarrito();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Carrito obtenerCarrito(int id) {
        Usuario usuario = usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (usuario == null) {
            throw new UsuarioNoEncontrado(String.format("Usuario con ID %d no encontrado", id));
        }
        return usuario.getCarrito();

    }
}