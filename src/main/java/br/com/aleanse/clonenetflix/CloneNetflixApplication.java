package br.com.aleanse.clonenetflix;

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
		System.out.println("ola");
	}
}
