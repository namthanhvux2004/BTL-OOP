package com.StartUp.StudyW.dto.request;

import com.StartUp.StudyW.entity.Deck;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashcardCreationRequest
{
    String id; // ID flashcard
    String question; // Nội dung câu hỏi
    String answer; // Nội dung câu trả lời
    Deck deck; // Bộ đề mà Flashcard thuộc về
}
