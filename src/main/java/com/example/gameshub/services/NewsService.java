package com.example.gameshub.services;

import com.example.gameshub.adapter.repositories.NewsRepository;
import com.example.gameshub.domain.models.News;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsService {
    private final NewsRepository NewsRepository;

    public NewsService(NewsRepository NewsRepository) {
        this.NewsRepository = NewsRepository;
    }

    public List<News> allNews() {
        return NewsRepository.findAll();
    }

    public ResponseEntity<News> find_News(Long id) {
        Optional<News> optionalNews = NewsRepository.findById(id);
        return optionalNews.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public void saveNews(News News) {
        NewsRepository.save(News);
    }

    public void deleteNewsById(Long id) {
        NewsRepository.deleteById(id);
    }

    public void updateNews(Long id, News updatedNews) {
        News existingNews = NewsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setCreatedAt(updatedNews.getCreatedAt());
        NewsRepository.save(existingNews);
    }

    /*public List<News> get5News() {
        List<News> list = new ArrayList<>();
        List<News> list1 = new ArrayList<>();
        NewsRepository.findAll().addAll(list);
        for (int i = 0; i < 5; i++){
            list1.add(list.get(5 - i - 1));
        }
        return list1;
    }*/
}
