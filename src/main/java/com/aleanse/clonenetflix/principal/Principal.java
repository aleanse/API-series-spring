package com.aleanse.clonenetflix.principal;
import com.aleanse.clonenetflix.models.*;
import com.aleanse.clonenetflix.repository.SerieRepository;
import com.aleanse.clonenetflix.service.ConsumoApi;
import com.aleanse.clonenetflix.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7c3783a2";
    private List<DadoSerie> dadoSeries = new ArrayList<>();

    private SerieRepository repositorio;

    private List<Serie> series = new ArrayList<>();

   public Principal( SerieRepository repositorio){

        this.repositorio = repositorio;

    }


    public void exibeMenu() throws JsonProcessingException {
        int opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas            
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = Integer.parseInt(leitura.nextLine());

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarSerieWeb() throws JsonProcessingException {
        DadoSerie dados = getDadosSerie();
        Serie serie =  new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadoSerie getDadosSerie() throws JsonProcessingException {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadoSerie dados = conversor.obterDados(json, DadoSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() throws JsonProcessingException {
        System.out.println("Escolha uma serie pelo nome:");
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serie = series.stream().filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase())).findFirst();
        if(serie.isPresent()){
            var serieEncontrada = serie.get();
            List<DadoTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadoTemporada dadosTemporada = conversor.obterDados(json, DadoTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream().flatMap(d -> d.episodios().stream().map(e -> new Episodio(d.numero(), e))).collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        }else {
            System.out.println("Série não encontrada!");
        }

    }
    private void listarSeriesBuscadas(){
       series = repositorio.findAll();
       series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);
    }



}
