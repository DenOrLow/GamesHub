package com.example.gameshub.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videogames")
@Getter
@Setter
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videogame_id")
    private Long videogameId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}