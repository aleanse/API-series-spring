package com.aleanse.clonenetflix.models;

import com.aleanse.clonenetflix.service.IConverteDados;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoTemporada(@JsonAlias("Season") Integer numero,
                            @JsonAlias("Episodes") List<DadoEpisodio> episodios) {
}
