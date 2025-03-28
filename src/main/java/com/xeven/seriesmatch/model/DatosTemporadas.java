package com.xeven.seriesmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTemporadas(
        @JsonAlias("Season") Integer numeroTemporada,
        @JsonAlias("Episodes") List<DatosEpisodio>episodios
) {
}
