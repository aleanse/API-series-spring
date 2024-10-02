package com.aleanse.clonenetflix.service;

import com.aleanse.clonenetflix.DTO.EpisodioDTO;
import com.aleanse.clonenetflix.DTO.SerieDTO;
import com.aleanse.clonenetflix.models.Categoria;
import com.aleanse.clonenetflix.models.Episodio;
import com.aleanse.clonenetflix.models.Serie;
import com.aleanse.clonenetflix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    SerieRepository repository;

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repository.findAll());

    }
    public List<SerieDTO> obterTop5Series(){
        return converteDados( repository.findTop5ByOrderByAvaliacaoDesc());
    }
    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository. top5SeriePorDataDeLancamento());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            SerieDTO serieDTO = new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getSinopse(),s.getAtores(),s.getPoster(),s.getGenero());
            return serieDTO;
        }else
            return null;
    }

    private List<SerieDTO> converteDados(List<Serie> series){
        return series.stream().map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getSinopse(),s.getAtores(),s.getPoster(),s.getGenero())).collect(Collectors.toList());
    }

    public List<EpisodioDTO> buscaEpisodios(Long id){
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            List<EpisodioDTO> episodio = s.getEpisodios().stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio())).collect(Collectors.toList());
            return episodio;
        }else
            return null;
    }
    public List<EpisodioDTO> buscaTemporada(Long id, Long temp) {
        return repository.buscaPorTemporada(id, temp).stream().map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio())).collect(Collectors.toList());

    }






































    public List<SerieDTO> buscarSeriePorCategoria(String genero) {
        Categoria categoriaEnum = Categoria.fromPortugues(genero);
        List<Serie> series = repository.findByGenero(categoriaEnum);
        return series.stream().map(s -> new SerieDTO(s.getId(),s.getTitulo(),s.getTotalTemporadas(),s.getAvaliacao(),s.getSinopse(),s.getAtores(),s.getPoster(),s.getGenero())).collect(Collectors.toList());

    }
}


