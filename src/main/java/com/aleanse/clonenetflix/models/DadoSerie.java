package com.aleanse.clonenetflix.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DadoSerie(@JsonAlias("Title") String titulo,
                        @JsonAlias("totalSeasons") Integer totalTemporadas,
                        @JsonAlias("imdbRating") String avaliacao,
                        @JsonAlias("Plot") String sinopse,
                        @JsonAlias("Actors") String atores,
                        @JsonAlias("Poster") String poster,
                        @JsonAlias("Genre") String genero)
                        {
}
