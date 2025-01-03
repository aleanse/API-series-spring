package com.aleanse.clonenetflix.models;

import com.aleanse.clonenetflix.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;


@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private String sinopse;
    private String atores;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Episodio> episodios = new ArrayList<>();





    public List<Episodio> getEpisodios() {

        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }


    public Serie(){

    }
    public Serie(DadoSerie dadoSerie){
        this.titulo = dadoSerie.titulo();
        this.totalTemporadas = dadoSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadoSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString((dadoSerie.genero().split(",")[0].trim()));
        this.atores = dadoSerie.atores();
        this.poster = dadoSerie.poster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadoSerie.sinopse().trim()) ;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", genero=" + genero + '\''+
                ", episodios=" + episodios + '\'';
    }
}
