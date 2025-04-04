package com.dsfyr.ecommerce.model;

import com.dsfyr.ecommerce.Error.CantidadNoValida;
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
    private double totalVentas = 0;

    public Tienda(List<Usuario> usuarios, List<Producto> productos) {
        this.usuarios = usuarios;
        this.productos = productos;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Carrito obtenerCarrito(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        return usuario.getCarrito();
    }

    public Carrito agregarItemCarrito(int idUsuario, String sku, int cantidad, ManejadorReglasService manejadorReglas) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Producto producto = buscarProductoPorSku(sku);
        usuario.agregarItemCarrito(producto, cantidad, manejadorReglas);
        return usuario.getCarrito();
    }

    public Carrito reducirCantidadItemCarrito(int idUsuario, String sku, int cantidad) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        usuario.reducirCantidadItemCarrito(sku, cantidad);
        return usuario.getCarrito();
    }

    public Carrito eliminarItemCarrito(int idUsuario, String sku) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        usuario.eliminarItemCarrito(sku);
        return usuario.getCarrito();
    }

    public double confirmarCompra(int idUsuario) {
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        double total = usuario.getTotalCarrito();
        if (total > 0) {
            Carrito carrito = usuario.getCarrito();
            descontarUnidades(carrito.getItems());
            totalVentas += total;
            usuario.vaciarCarrito();
        } else {
            throw new CantidadNoValida("El carrito está vacío. No se puede confirmar la compra.");
        }
        return total;
    }

    private Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (usuario == null) {
            throw new UsuarioNoEncontrado("Usuario con ID %d no encontrado.".formatted(id));
        }
        return usuario;
    }

    private Producto buscarProductoPorSku(String sku) {
        Producto producto = productos.stream().filter(p -> p.getSku().equals(sku)).findFirst().orElse(null);
        if (producto == null) {
            throw new ProductoNoEncontrado("Producto con SKU %s no encontrado.".formatted(sku));
        }
        return producto;
    }

    private void descontarUnidades(List<Item> items) {
        for (Item item : items) {
            Producto producto = buscarProductoPorSku(item.getProducto().getSku());
            if (producto.tieneUnidades(item.getCantidad())) {
                producto.descontarUnidades(item.getCantidad());
            } else {
                throw new CantidadNoValida("No hay suficientes unidades disponibles para el producto con SKU %s.".formatted(item.getProducto().getSku()));
            }
        }
        
    }
}