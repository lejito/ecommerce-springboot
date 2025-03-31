package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.model.Carrito;
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

    public boolean agregarItemCarrito(int idUsuario, String sku, int cantidad) {

        for (Usuario usuario : usuarios) {
            if (usuario.getId() == idUsuario) {
                Producto producto = productos.stream().filter(p -> p.getSku().equals(sku)).findFirst().orElse(null);
                if (producto == null) {
                    return false;
                }
                return usuario.agregarItemAlCarrito(producto, cantidad);
            }
        }
        return false;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Carrito obtenerCarrito(int id) {
       return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);

    }
}