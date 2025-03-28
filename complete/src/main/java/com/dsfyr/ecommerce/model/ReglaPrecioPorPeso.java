package com.dsfyr.ecommerce.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReglaPrecioPorPeso implements ReglaPrecio {
    @Override
    public boolean esAplicable(String sku) {
        Pattern pattern = Pattern.compile("^WE");
        Matcher matcher = pattern.matcher(sku);
        return matcher.find();
    }

    @Override
    public float calcularTotal(int cantidad, float precio) {
        return cantidad * precio * 1000;
    }
}
