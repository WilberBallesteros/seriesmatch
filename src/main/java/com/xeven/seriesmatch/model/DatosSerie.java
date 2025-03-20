package com.xeven.seriesmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) //para q no mapee todos los demas atributos q vienen de la api, los ignore
public record DatosSerie(
        //el @JsonAlias muestra  en el nombre q viene de la API, luego el nombre
        //q yo le doy a este
        @JsonAlias("Title") String titulo, //jsonAlias solo sirve para leer el contenido q vene de la API
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("imdbRating") String evaluacion

        //podemos leer y escribir, es decir, enviar datos en ese modelo, como agregar una nueva serie
        //@JsonProperty("imdbRating") String evaluacion2
) {
}
