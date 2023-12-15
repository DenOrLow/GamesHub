package com.example.gameshub.services;

import com.example.gameshub.domain.models.Videogame;
import com.example.gameshub.adapter.repositories.VideogameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VideogameService {
    private final VideogameRepository videogameRepository;

    public VideogameService(VideogameRepository videogameRepository) {
        this.videogameRepository = videogameRepository;
    }

    public List<Videogame> allVideogames() {
        return videogameRepository.findAllNews();
    }

    public ResponseEntity<Videogame> find_Videogame(Long id) {
        Optional<Videogame> optionalVideogame = videogameRepository.findById(id);

        return optionalVideogame.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void saveVideogame(Videogame news) {
        videogameRepository.save(news);
    }

    public void deleteVideogameById(Long id) {
        videogameRepository.deleteById(id);
    }

    public void updateVideogame(Long id, Videogame updateNews) {
        Videogame existingVideogame = videogameRepository.findById(id).orElseThrow(() -> new RuntimeException("Videogame not found"));

        existingVideogame.setTitle(updateNews.getTitle());
        existingVideogame.setDescription(updateNews.getDescription());

        videogameRepository.save(existingVideogame);
    }
}
