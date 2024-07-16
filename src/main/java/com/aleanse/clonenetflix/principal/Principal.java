package com.aleanse.clonenetflix.principal;
import com.aleanse.clonenetflix.models.DadoEpisodio;
import com.aleanse.clonenetflix.models.DadoSerie;
import com.aleanse.clonenetflix.models.DadoTemporada;
import com.aleanse.clonenetflix.models.Episodio;
import com.aleanse.clonenetflix.service.ConsumoApi;
import com.aleanse.clonenetflix.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7c3783a2";

    public void exibeMenu() throws JsonProcessingException {


        System.out.println("digite o nome da serie para buscar");
        String nomeSerie = leitura.nextLine();

        String json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadoSerie dadoSerie = conversor.obterDados(json,DadoSerie.class);
        System.out.println(dadoSerie);
		List<DadoTemporada> temporadaLista = new ArrayList<>();
		for (int i = 1; i <= dadoSerie.totalTemporadas(); i++){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY + "&season="+i);
			DadoTemporada dadoTemporada = conversor.obterDados(json,DadoTemporada.class);
			temporadaLista.add(dadoTemporada);
		}
        temporadaLista.forEach(System.out::println);
        for(int i=0; i < dadoSerie.totalTemporadas(); i++){
            List<DadoEpisodio> episodiosTemporadas = temporadaLista.get(i).episodios();
            for(int j = 0; j < episodiosTemporadas.size(); j++){
                System.out.println(episodiosTemporadas.get(j).titulo());
            }
        }
        List<DadoEpisodio> dadoEpisodios = temporadaLista.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());




        dadoEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadoEpisodio::avaliacao).reversed())
                .limit(5).forEach(System.out::println);


        List<Episodio> episodios =  temporadaLista.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(),d))).collect(Collectors.toList());
        System.out.println("digite um trecho do titulo do episodio");
        String trechoTitulo = leitura.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado");
            System.out.println(episodioBuscado.get().getTemporada());
        }else{
            System.out.println("Episodio não encontrado");
        }
        Map<Integer,Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream().filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("Média " + est.getAverage());
        System.out.println("Mélhor episodio "+ est.getMax());
    }
}
