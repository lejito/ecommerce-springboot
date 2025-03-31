package com.dsfyr.ecommerce;

import com.dsfyr.ecommerce.model.Tienda;
import com.dsfyr.ecommerce.model.Usuario;
import com.dsfyr.ecommerce.model.Producto;
import com.dsfyr.ecommerce.model.Carrito;
import com.dsfyr.ecommerce.service.JsonDataLoaderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/tienda")  // Prefijo para todas las rutas
public class TiendaController {

    private final Tienda tienda;  // Se declara `final` porque se inyecta en el constructor

    // Inyección de dependencia
    public TiendaController(JsonDataLoaderService jsonDataLoaderService) {
        List<Usuario> usuarios = jsonDataLoaderService.cargarUsuarios();
        List<Producto> productos = jsonDataLoaderService.cargarProductos();
        this.tienda = new Tienda(usuarios, productos);
    }

    // Obtener todos los usuarios (Spring lo convierte automáticamente a JSON)
    @GetMapping("/usuarios")
    public List<Usuario> usuarios() {
        return tienda.getUsuarios();
    }

    //  Obtener el carrito de un usuario por ID
    @GetMapping("/usuarios/{id}/carrito")
    public Carrito obtenerCarrito(@PathVariable int id) {
        Carrito carrito = tienda.obtenerCarrito(id);
        if (carrito != null) {
            return carrito;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
    }

    // Obtener todos los productos (Spring lo convierte automáticamente a JSON)
    @GetMapping("/productos")
    public List<Producto> productos() {
        return tienda.getProductos();
    }

}
