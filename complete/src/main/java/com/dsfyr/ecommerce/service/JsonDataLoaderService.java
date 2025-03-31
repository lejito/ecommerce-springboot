package com.dsfyr.ecommerce.service;

import com.dsfyr.ecommerce.model.Producto;
import com.dsfyr.ecommerce.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class JsonDataLoaderService {

    private static final String DATA_DIR = "src/main/resources/data"; // Directorio donde están los archivos JSON
    private final ObjectMapper objectMapper;

    public JsonDataLoaderService() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Usuario> cargarUsuarios() {
        return leerArchivoJson("usuarios.json", new TypeReference<List<Usuario>>() {});
    }

    public List<Producto> cargarProductos() {
        return leerArchivoJson("productos.json", new TypeReference<List<Producto>>() {});
    }

    private <T> List<T> leerArchivoJson(String fileName, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(DATA_DIR, fileName);
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + fileName);
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }
}
