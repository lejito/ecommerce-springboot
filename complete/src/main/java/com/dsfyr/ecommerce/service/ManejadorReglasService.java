package com.dsfyr.ecommerce.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.dsfyr.ecommerce.model.ReglaPrecio;
import com.dsfyr.ecommerce.model.ReglaPrecioNormal;
import com.dsfyr.ecommerce.model.ReglaPrecioPorPeso;
import com.dsfyr.ecommerce.model.ReglaPrecioEspecial;

@Service
public class ManejadorReglasService {
    private List<ReglaPrecio> reglas = new ArrayList<ReglaPrecio>();

    public ManejadorReglasService() {
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
