package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.Error.CantidadNoValida;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

import  java.util.ArrayList;
import  java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Carrito{
    private List<Item> items = new ArrayList<>();

    @JsonProperty("total")
    public double getTotal() {
        return items.stream()
                    .mapToDouble(item -> item.calcularTotal())
                    .sum();
    }

    public void agregarItem(Producto producto, int cantidad,ManejadorReglasService manejadorReglas) {
        Item currentItem = items.stream().filter(i -> i.getProducto().getSku().equals(producto.getSku())).findFirst().orElse(null);
        if (currentItem == null) { 
            currentItem = new Item(producto, 0, manejadorReglas);
        }
        if (currentItem.getCantidad() + cantidad > producto.getUnidadesDisponibles()) {
        throw new CantidadNoValida(String.format("Cantidad %d no válida para el producto con SKU %s con %d  unidad(es)", cantidad, producto.getSku(), producto.getUnidadesDisponibles()));
        }
        if (currentItem.getCantidad() == 0) {
            items.add(currentItem);
        }
        currentItem.setCantidad(currentItem.getCantidad() + cantidad);
    }

    public void reducirCantidadItem(String sku, int cantidad, ManejadorReglasService manejadorReglas) {
        Item item = items.stream()
                         .filter(i -> i.getProducto().getSku().equals(sku))
                         .findFirst()
                         .orElse(null);
    
        if (item == null) { 
            throw new IllegalArgumentException("El producto con SKU " + sku + " no está en el carrito.");
        }
    
        if (item.getCantidad() <= cantidad) {
            items.remove(item); // Si la cantidad es menor o igual a la existente, eliminar el producto
        } else {
            item.setCantidad(item.getCantidad() - cantidad); // Si la cantidad es mayor, reducir la cantidad
        }
    }
    
    
}