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
public class DeckUpdateRequest
{
    String name; // Tên bộ đề
    String description; // Mô tả bộ đ
    Set<Flashcard> flashcards; // Mối quan hệ One-to-Many với Flashcard
}
