package com.aleanse.clonenetflix;

import com.aleanse.clonenetflix.principal.Principal;
import com.aleanse.clonenetflix.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloneNetflixApplication{



	public static void main(String[] args) {

		SpringApplication.run(CloneNetflixApplication.class, args);
	}


}