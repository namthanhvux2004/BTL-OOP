package com.StartUp.StudyW.dto.response;

import com.StartUp.StudyW.entity.Deck;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlashcardResponse
{
    String id; // ID flashcard
    String question; // Nội dung câu hỏi
    String answer; // Nội dung câu trả lời
    Deck deck; // Bộ đề mà Flashcard thuộc về
}
