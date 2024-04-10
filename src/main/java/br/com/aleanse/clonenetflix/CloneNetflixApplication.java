package br.com.aleanse.clonenetflix;
import br.com.aleanse.clonenetflix.model.DadoSerie;
import br.com.aleanse.clonenetflix.model.DadosTemporadas;
import br.com.aleanse.clonenetflix.model.DadosEpisodio;
import br.com.aleanse.clonenetflix.principal.Principal;
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
		Principal principal = new Principal();
		principal.exibeMenu();







	}
}
