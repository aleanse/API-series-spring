package com.aleanse.clonenetflix.repository;

import com.aleanse.clonenetflix.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
