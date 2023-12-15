package com.example.gameshub.adapter.repositories;

import com.example.gameshub.domain.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
