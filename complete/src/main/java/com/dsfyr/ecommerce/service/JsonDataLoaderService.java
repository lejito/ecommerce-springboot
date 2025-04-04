package com.dsfyr.ecommerce.service;

import com.dsfyr.ecommerce.model.Producto;
import com.dsfyr.ecommerce.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class JsonDataLoaderService {

    private final ObjectMapper objectMapper;

    public JsonDataLoaderService() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Usuario> cargarUsuarios() {
        return leerArchivoJson("data/usuarios.json", new TypeReference<List<Usuario>>() {
        });
    }

    public List<Producto> cargarProductos() {
        return leerArchivoJson("data/productos.json", new TypeReference<List<Producto>>() {
        });
    }

    private <T> List<T> leerArchivoJson(String filePath, TypeReference<List<T>> typeReference) {
        try (InputStream inputStream = new ClassPathResource(filePath).getInputStream()) {
            return objectMapper.readValue(inputStream, typeReference);
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + filePath);
            e.printStackTrace();
            return List.of(); // Retorna una lista vacía en caso de error
        }
    }
}
