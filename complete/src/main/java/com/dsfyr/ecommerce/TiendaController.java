package com.dsfyr.ecommerce;

import com.dsfyr.ecommerce.model.Tienda;
import com.dsfyr.ecommerce.model.Usuario;
import com.dsfyr.ecommerce.model.Producto;
import com.dsfyr.ecommerce.model.Carrito;
import com.dsfyr.ecommerce.service.JsonDataLoaderService;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

import org.springframework.web.bind.annotation.*;
import com.dsfyr.ecommerce.DTO.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tienda")  // Prefijo para todas las rutas
public class TiendaController {

    private final Tienda tienda;  // Se declara `final` porque se inyecta en el constructor
    private final ManejadorReglasService manejadorReglas; // Se declara `final` porque se inyecta en el constructor
    // Inyección de dependencia
    public TiendaController(JsonDataLoaderService jsonDataLoaderService, ManejadorReglasService manejadorReglas) {
        List<Usuario> usuarios = jsonDataLoaderService.cargarUsuarios();
        List<Producto> productos = jsonDataLoaderService.cargarProductos();
        this.tienda = new Tienda(usuarios, productos);
        this.manejadorReglas = manejadorReglas; 
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
        return carrito;
       
        
    }

    // Obtener todos los productos (Spring lo convierte automáticamente a JSON)
    @GetMapping("/productos")
    public List<Producto> productos() {
        return tienda.getProductos();
    }

    // Ruta para agregar un producto al carrito
    @PostMapping("/usuarios/{id}/carrito")
    public Carrito agregarProductoAlCarrito(@PathVariable int id, @Valid @RequestBody RequestBodyAgregarCarritoDTO requestBody) {
            Carrito carrito = tienda.agregarItemCarrito(id, requestBody.getSku(), requestBody.getCantidad(), manejadorReglas);
            return carrito;
    
    }
       
    

}
