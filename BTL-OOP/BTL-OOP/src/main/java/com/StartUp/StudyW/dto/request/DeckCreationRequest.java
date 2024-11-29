package com.StartUp.StudyW.dto.request;

import com.StartUp.StudyW.entity.Flashcard;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeckCreationRequest
{
    String id;
    String name; // Tên bộ đề
    String description; // Mô tả bộ đề
    Set<Flashcard> flashcards; // Mối quan hệ One-to-Many với Flashcard
}
