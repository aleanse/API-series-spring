package com.aleanse.clonenetflix.models;

import com.aleanse.clonenetflix.service.traducao.ConsultaMyMemory;

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
        this.sinopse = ConsultaMyMemory.obterTraducao(dadoSerie.sinopse().trim()) ;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", sinopse='" + sinopse + '\'' +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", genero=" + genero + '\'';
    }
}
