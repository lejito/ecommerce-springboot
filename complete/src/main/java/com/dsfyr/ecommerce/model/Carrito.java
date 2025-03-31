package com.dsfyr.ecommerce.model;
import  com.dsfyr.ecommerce.model.Item;
import  java.util.ArrayList;
import  java.util.List;

public class Carrito{
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void agregarItem(Producto producto, int cantidad) {
        Item currentItem = items.stream().filter(i -> i.getProducto().getSku().equals(producto.getSku())).findFirst().orElse(null);
        if (currentItem === null) {
            currentItem = new Item(producto, 0);
        }


        items.add(item);

    }

}