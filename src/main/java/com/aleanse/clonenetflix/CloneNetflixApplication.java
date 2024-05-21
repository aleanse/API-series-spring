package com.aleanse.clonenetflix;

import com.aleanse.clonenetflix.service.ConsumoApi;
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
		String json = consumoApi.obterDados("https://www.omdbapi.com/?t=avengers&apikey=7c3783a2");
		System.out.println(json);

	}

}
