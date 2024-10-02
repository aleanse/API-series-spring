package com.aleanse.clonenetflix.controller;
import com.aleanse.clonenetflix.DTO.EpisodioDTO;
import com.aleanse.clonenetflix.DTO.SerieDTO;
import com.aleanse.clonenetflix.models.Categoria;
import com.aleanse.clonenetflix.models.Episodio;
import com.aleanse.clonenetflix.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;


    @GetMapping
    public List<SerieDTO> obterSeries(){
        return service.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series(){

        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos(){
        return service.obterLancamentos();

    }
    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id ){
        return service.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> buscaEpisodios(@PathVariable Long id){
        return service.buscaEpisodios(id);
    }
    @GetMapping("/{id}/temporadas/{temp}")
    public List<EpisodioDTO> buscaTemporada(@PathVariable Long id, @PathVariable Long temp){
        return service.buscaTemporada(id,temp);
    }

    @GetMapping("/categoria/{categoria}")
    public List<SerieDTO> buscaCategoria(@PathVariable String categoria){
        return service.buscarSeriePorCategoria(categoria);
    }

}
