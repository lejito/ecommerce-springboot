package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.Error.ProductoNoEncontrado;
import com.dsfyr.ecommerce.Error.UsuarioNoEncontrado;
import com.dsfyr.ecommerce.service.ManejadorReglasService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tienda {
      private List<Usuario> usuarios = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    public Carrito agregarItemCarrito(int idUsuario, String sku, int cantidad, ManejadorReglasService manejadorReglas) {

        Usuario usuario = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst().orElse(null);
        if(usuario == null){
            throw new UsuarioNoEncontrado("Usuario con ID %d no encontrado".formatted(idUsuario));
        }
        Producto producto = productos.stream().filter(p -> p.getSku().equals(sku)).findFirst().orElse(null);
        if (producto == null) {
            throw new ProductoNoEncontrado("Producto con SKU %s no encontrado".formatted(sku));
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
            throw new UsuarioNoEncontrado("Usuario con ID %d no encontrado".formatted(id));
        }
        return usuario.getCarrito();

    }
}