package com.dsfyr.ecommerce.model;
import com.dsfyr.ecommerce.Error.CantidadNoValida;
import com.dsfyr.ecommerce.service.ManejadorReglasService;

import  java.util.ArrayList;
import  java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Carrito{
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }
    @JsonProperty("total")
    public double getTotal() {
        return items.stream()
                    .mapToDouble(item -> item.calcularTotal())
                    .sum();
    }

    @JsonProperty("items")
    public List<Item> getCalculatedItems() {
        return items;
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

}