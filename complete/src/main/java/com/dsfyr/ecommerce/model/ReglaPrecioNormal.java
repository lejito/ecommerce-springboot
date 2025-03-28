package com.dsfyr.ecommerce.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReglaPrecioNormal implements ReglaPrecio {
    @Override
    public boolean esAplicable(String sku) {
        Pattern pattern = Pattern.compile("^EA");
        Matcher matcher = pattern.matcher(sku);
        return matcher.find();
    }

    @Override
    public float calcularTotal(int cantidad, float precio) {
        return cantidad * precio;
    }
}
