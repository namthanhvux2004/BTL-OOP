package com.StartUp.StudyW.repository;

import com.StartUp.StudyW.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, String>
{
    boolean existsByQuestion(String question);
    // Tìm tất cả flashcard thuộc một Deck với deckId
    List<Flashcard> findByDeckId(String deckId);
}
