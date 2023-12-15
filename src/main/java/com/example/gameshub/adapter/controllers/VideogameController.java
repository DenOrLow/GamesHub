package com.example.gameshub.adapter.controllers;


import com.example.gameshub.domain.models.Videogame;
import com.example.gameshub.services.VideogameService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Игры")
@RestController
@SecurityRequirement(name = "JWT")
@RequestMapping("/games")
public class VideogameController {

    private final VideogameService videogameService;

    public VideogameController(VideogameService videogameService) {
        this.videogameService = videogameService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Videogame>> allVideogames() {
        List<Videogame> news = videogameService.allVideogames();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(news);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> videogames(Model model, @PathVariable Long id) {
        Videogame videogames = videogameService.find_Videogame(id).getBody();

        if (videogames != null) {
            return ResponseEntity.ok(videogames);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Videogame> add_Videogame(@RequestBody Videogame videogame) {
        try {
            videogameService.saveVideogame(videogame);
            return ResponseEntity.ok(videogame);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVideogame(@PathVariable Long id) {
        try {
            videogameService.deleteVideogameById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Void> updateVideogame(@PathVariable Long id, @RequestBody Videogame updatedNews) {
        try {
            videogameService.updateVideogame(id, updatedNews);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}