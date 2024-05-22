package com.aleanse.clonenetflix.models;

import com.aleanse.clonenetflix.service.IConverteDados;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoEpisodio(
    @JsonAlias("Title") String titulo,
    @JsonAlias("Episode") Integer numero,
    @JsonAlias("imdbRating")  String avaliacao,
    @JsonAlias("Released") String dataDeLançamento) {
}
