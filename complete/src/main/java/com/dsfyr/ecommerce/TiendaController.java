package com.dsfyr.ecommerce;

import com.dsfyr.ecommerce.model.Tienda;
import com.dsfyr.ecommerce.model.Usuario;
import com.dsfyr.ecommerce.model.Producto;
import com.dsfyr.ecommerce.model.Carrito;
import com.dsfyr.ecommerce.service.JsonDataLoaderService;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.dsfyr.ecommerce.DTO.*;
import java.util.List;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tienda")  // Prefijo para todas las rutas
@Tag(name = "Tienda", description = "Endpoints para gestionar la tienda")
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
    @Operation(summary = "Listar usuarios", description = "Obtiene la lista de usuarios de la aplicación")
    public ResponseDTO usuarios() {
        return new ResponseDTO("Lista de usuarios", true, tienda.getUsuarios(), HttpStatus.OK);
    }

    //  Obtener el carrito de un usuario por ID
    @GetMapping("/usuarios/{id}/carrito")
    @Operation(summary = "Obtener carrito", description = "Obtiene el carrito de un usuario por ID")
    public ResponseDTO obtenerCarrito(@PathVariable int id) {
        Carrito carrito = tienda.obtenerCarrito(id);
        return new ResponseDTO("Carrito del usuario", true, carrito, HttpStatus.OK);
       
        
    }

    // Obtener todos los productos (Spring lo convierte automáticamente a JSON)
    @GetMapping("/productos")
    @Operation(summary = "Listar productos", description = "Obtiene la lista de prucductos disponibles en la aplicación")
    public ResponseDTO productos() {
        return new ResponseDTO("Lista de productos", true, tienda.getProductos(), HttpStatus.OK);
    }

    // Ruta para agregar un producto al carrito
    @PostMapping("/usuarios/{id}/carrito")
    @Operation(summary = "Agregar un producto al carrito", description = "Agregar un producto al carrito de un usuario por ID")
    public ResponseDTO agregarProductoAlCarrito(@PathVariable int id, @Valid @RequestBody RequestBodyAgregarCarritoDTO requestBody) {
            Carrito carrito = tienda.agregarItemCarrito(id, requestBody.getSku(), requestBody.getCantidad(), manejadorReglas);
            return new ResponseDTO("Producto agregado al carrito", true, carrito, HttpStatus.CREATED);
    
    }

    // Ruta para reducir la cantidad de un producto en el carrito
    @DeleteMapping("/usuarios/{id}/carrito")
    @Operation(summary = "Reducir cantidad o eliminar item en el carrito", description = "Reduce la cantidad de un producto en el carrito de un usuario por ID")
    public ResponseDTO reducirCantidadProductoCarrito(@PathVariable int id, @Valid @RequestBody RequestBodyReducirCantidadCarritoDTO requestBody) {
        Carrito carrito = tienda.reducirCantidadItemCarrito(id, requestBody.getSku(), requestBody.getCantidad());
        return new ResponseDTO("Cantidad reducida en el carrito", true, carrito, HttpStatus.OK);
    }
       
    

}
