package com.xeven.seriesmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeven.seriesmatch.model.DatosSerie;

public class ConvierteDatos implements IConvierteDatos{

    //crear una nueva instancia q nos permita leer los valores q vienen de la API
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase); //lee el valor json y lo transforma en esa clase q le vamos a pasar
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
