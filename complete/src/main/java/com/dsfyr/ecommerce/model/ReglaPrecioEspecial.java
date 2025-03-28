package com.dsfyr.ecommerce.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReglaPrecioEspecial implements ReglaPrecio {
    @Override
    public boolean esAplicable(String sku) {
        Pattern pattern = Pattern.compile("^SP");
        Matcher matcher = pattern.matcher(sku);
        return matcher.find();
    }

    @Override
    public float calcularTotal(int cantidad, float precio) {
        float subtotal = cantidad * precio;
        int setsDe3 = cantidad / 3;
        float descuento = setsDe3 < 3 ? 0.2f * setsDe3 : 0.5f;
        return subtotal * (1 - descuento);
    }
}
