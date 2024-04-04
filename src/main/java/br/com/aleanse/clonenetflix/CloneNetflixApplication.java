package br.com.aleanse.clonenetflix;

import br.com.aleanse.clonenetflix.model.DadoSerie;
import br.com.aleanse.clonenetflix.model.DadosTemporadas;
import br.com.aleanse.clonenetflix.service.ConsumoApi;
import br.com.aleanse.clonenetflix.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CloneNetflixApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(CloneNetflixApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=the+flash&apikey=7c3783a2");
		ConverteDados conversor = new ConverteDados();
		DadoSerie dados = conversor.obterDados(json,DadoSerie.class);
		System.out.println(dados);
		List<DadosTemporadas> temporadas = new ArrayList<>();
		for (int i = 1; i<=dados.totalTemporadas(); i++){
			 json = consumoApi.obterDados("http://www.omdbapi.com/?t=the+flash&season="+i+"&apikey=7c3783a2");
			 DadosTemporadas dadosTemporadas = conversor.obterDados(json,DadosTemporadas.class);
			 temporadas.add(dadosTemporadas);
		}
		temporadas.forEach(System.out::println);



	}
}
