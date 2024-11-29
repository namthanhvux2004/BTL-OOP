package com.StartUp.StudyW.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id; // ID flashcard

    @Column(nullable = false)
    String question; // Nội dung câu hỏi

    @Column(nullable = false)
    String answer; // Nội dung câu trả lời

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = true) // Mối quan hệ N - 1 với Deck
    Deck deck; // Bộ đề mà Flashcard thuộc về

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer); // Sử dụng các thuộc tính cơ bản để tính hashCode
    }
}
