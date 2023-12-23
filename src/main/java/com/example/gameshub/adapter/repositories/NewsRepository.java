package com.example.gameshub.adapter.repositories;

import com.example.gameshub.domain.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
