package com.aleanse.clonenetflix;

import com.aleanse.clonenetflix.models.DadoEpisodio;
import com.aleanse.clonenetflix.models.DadoSerie;
import com.aleanse.clonenetflix.service.ConsumoApi;
import com.aleanse.clonenetflix.service.ConverteDados;
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
		String json = consumoApi.obterDados("https://www.omdbapi.com/?t=the+flash&season=1&episode=1&apikey=7c3783a2");
		ConverteDados conversor = new ConverteDados();
		DadoEpisodio dadoEpisodio = conversor.obterDados(json,DadoEpisodio.class);
		System.out.println(dadoEpisodio);

	}

}
