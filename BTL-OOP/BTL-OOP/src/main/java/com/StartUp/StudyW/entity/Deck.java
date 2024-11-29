package com.StartUp.StudyW.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id; // ID bộ đề

    @Column(nullable = false)
    String name; // Tên bộ đề

    @Column
    String description; // Mô tả bộ đề

    @JsonManagedReference
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Flashcard> flashcards; // Mối quan hệ One-to-Many với Flashcard

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description); // Sử dụng các thuộc tính cơ bản để tính hashCode
    }
}
