package br.com.aleanse.clonenetflix;

import br.com.aleanse.clonenetflix.model.DadoSerie;
import br.com.aleanse.clonenetflix.model.DadosEpisodio;
import br.com.aleanse.clonenetflix.service.ConsumoApi;
import br.com.aleanse.clonenetflix.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloneNetflixApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(CloneNetflixApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=supernatural&apikey=7c3783a2");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadoSerie dados = conversor.obterDados(json,DadoSerie.class);
		json = consumoApi.obterDados("http://www.omdbapi.com/?t=supernatural&season=3&episode=5&apikey=7c3783a2");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);



	}
}
