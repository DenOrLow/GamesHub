package com.example.gameshub.adapter.controllers;

import com.example.gameshub.domain.models.News;
import com.example.gameshub.services.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/news")
@SecurityRequirement(name = "JWT")
@Tag(name = "Новости")
public class NewsController {
    private final NewsService NewsService;

    public NewsController(NewsService NewsService) {
        this.NewsService = NewsService;
    }
    @Operation(
            summary = "Получить все новости")
    @GetMapping("/all")
    public List<News> all_News(Model model) {
        return NewsService.allNews();
    }

    @Operation(
            summary = "Информация по новости (по id)")
    @GetMapping("/{id}")
    public ResponseEntity<News> news(Model model, @PathVariable Long id) {
        News News = NewsService.find_News(id).getBody();

        if (News != null) {
            return ResponseEntity.ok(News);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @Operation(
            summary = "Создать новость")
    @PostMapping("/new")
    public ResponseEntity<News> add_news(@RequestBody News News) {
        try {
            NewsService.saveNews(News);
            return ResponseEntity.ok(News);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @Operation(
            summary = "Удалить новость (по id)")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        try {
            NewsService.deleteNewsById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Operation(
            summary = "Изменить новость")
    @PutMapping("/change/{id}")
    public ResponseEntity<Void> updateNews(@PathVariable Long id, @RequestBody News updatedNews) {
        try {
            NewsService.updateNews(id, updatedNews);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
