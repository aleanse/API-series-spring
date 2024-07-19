package com.aleanse.clonenetflix.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private String sinopse;
    private String atores;
    private String poster;
    private Categoria genero;

    public Serie(DadoSerie dadoSerie){
        this.titulo = dadoSerie.titulo();
        this.totalTemporadas = dadoSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadoSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString((dadoSerie.genero().split(",")[0].trim()));
        this.atores = dadoSerie.atores();
        this.poster = dadoSerie.poster();
        this.sinopse = dadoSerie.sinopse();

    }
}
