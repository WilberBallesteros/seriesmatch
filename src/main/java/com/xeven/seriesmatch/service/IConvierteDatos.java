package com.xeven.seriesmatch.service;

public interface IConvierteDatos {

    //<T> T tipos de datos generics q no sabemos q retorna, clase generica
    <T> T obtenerDatos(String json, Class<T> clase);
}
