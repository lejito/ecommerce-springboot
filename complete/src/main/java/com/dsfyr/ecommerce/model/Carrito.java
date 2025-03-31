package com.dsfyr.ecommerce.model;
import  com.dsfyr.ecommerce.model.Item;
import  java.util.ArrayList;
import  java.util.List;

public class Carrito{
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

}