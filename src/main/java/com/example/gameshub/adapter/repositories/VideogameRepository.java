package com.example.gameshub.adapter.repositories;

import com.example.gameshub.domain.models.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideogameRepository extends JpaRepository<Videogame, Long> {
    @Query("SELECT n FROM Videogame n")
    List<Videogame> findAllVideogames();
}