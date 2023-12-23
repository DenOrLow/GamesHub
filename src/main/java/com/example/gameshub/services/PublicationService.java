package com.example.gameshub.services;

import com.example.gameshub.domain.models.Publication;
import com.example.gameshub.adapter.repositories.PublicationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public List<Publication> allPublications() {
        return publicationRepository.findAll();
    }

    public ResponseEntity<Publication> find_Publication(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        return optionalPublication.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void savePublication(Publication publication) {
        publicationRepository.save(publication);
    }

    public void deletePublicationById(Long id) {
        publicationRepository.deleteById(id);
    }

    public void updatePublication(Long id, Publication updatedPublication) {
        Publication existingPublication = publicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Publication not found"));
        existingPublication.setTitle(updatedPublication.getTitle());
        existingPublication.setContent(updatedPublication.getContent());
        existingPublication.setCreatedAt(updatedPublication.getCreatedAt());
        publicationRepository.save(existingPublication);
    }
}
