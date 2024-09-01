package com.aleanse.clonenetflix.DTO;

import com.aleanse.clonenetflix.models.Serie;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record EpisodioDTO(
         Integer temporada,
         String titulo,
         Integer numeroEpisodio

) {
}

