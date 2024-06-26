package com.aleanse.clonenetflix.principal;
import com.aleanse.clonenetflix.models.DadoEpisodio;
import com.aleanse.clonenetflix.models.DadoSerie;
import com.aleanse.clonenetflix.models.DadoTemporada;
import com.aleanse.clonenetflix.service.ConsumoApi;
import com.aleanse.clonenetflix.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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



    }
}
