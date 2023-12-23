package com.example.gameshub.adapter.controllers;

import com.example.gameshub.domain.models.Publication;
import com.example.gameshub.services.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController

@RequestMapping("/publication")
@SecurityRequirement(name = "JWT")
@Tag(name = "Публикации")
public class PublicationController {
    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }
    @Operation(
            summary = "Получить все публикации")
    @GetMapping("/all")
    public List<Publication> all_publications(Model model) {
        return publicationService.allPublications();
    }

    @Operation(
            summary = "Информация по публикации (по id)")
    @GetMapping("/{id}")
    public ResponseEntity<Publication> publication(Model model, @PathVariable Long id) {
        Publication publication = publicationService.find_Publication(id).getBody();

        if (publication != null) {
            return ResponseEntity.ok(publication);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @Operation(
            summary = "Создать публикацию")
    @PostMapping("/new")
    public ResponseEntity<Publication> add_publication(@RequestBody Publication publication) {
        try {
            publicationService.savePublication(publication);
            return ResponseEntity.ok(publication);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @Operation(
            summary = "Удалить публикацию (по id)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        try {
            publicationService.deletePublicationById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(
            summary = "Изменить публикацию")
    @PutMapping("/change/{id}")
    public ResponseEntity<Void> updatePublication(@PathVariable Long id, @RequestBody Publication updatedPublication) {
        try {
            publicationService.updatePublication(id, updatedPublication);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
