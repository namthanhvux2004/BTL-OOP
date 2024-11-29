package com.StartUp.StudyW.repository;

import com.StartUp.StudyW.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck, String> {
    boolean existsByName(String name);
    // Tìm kiếm bộ đề theo tên hoặc mô tả chứa từ khóa
    List<Deck> findByNameContainingOrDescriptionContaining(String name, String description);
}
