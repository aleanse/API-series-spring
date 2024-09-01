package com.aleanse.clonenetflix.DTO;

import com.aleanse.clonenetflix.models.Categoria;
import com.aleanse.clonenetflix.models.Episodio;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record SerieDTO(
         Long id,
         String titulo,
         Integer totalTemporadas,
         Double avaliacao,
         String sinopse,
         String atores,
         String poster,
        Categoria genero
        ) {


}
