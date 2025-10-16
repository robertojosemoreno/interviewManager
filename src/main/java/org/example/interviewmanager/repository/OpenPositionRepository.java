package org.example.interviewmanager.repository;

import org.example.interviewmanager.repository.entity.OpenPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OpenPositionRepository extends JpaRepository<OpenPosition, UUID> {
    Page<OpenPosition> findOpenPositionsByLevel(String level, Pageable pageable);
}
