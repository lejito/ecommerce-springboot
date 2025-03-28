package com.dsfyr.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class ManejadorReglas {
    private List<ReglaPrecio> reglas = new ArrayList<ReglaPrecio>();

    public ManejadorReglas() {
        reglas.add(new ReglaPrecioNormal());
        reglas.add(new ReglaPrecioPorPeso());
        reglas.add(new ReglaPrecioEspecial());
    }

    public ReglaPrecio obtenerRegla(String sku) {
        for (ReglaPrecio reglaPrecio : reglas) {
            if (reglaPrecio.esAplicable(sku)) {
                return reglaPrecio;
            }
        }
        return new ReglaPrecioNormal();
    }
}
