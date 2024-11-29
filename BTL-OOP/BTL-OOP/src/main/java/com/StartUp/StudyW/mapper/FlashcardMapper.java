package com.StartUp.StudyW.mapper;

import com.StartUp.StudyW.dto.request.FlashcardCreationRequest;
import com.StartUp.StudyW.dto.request.FlashcardUpdateRequest;
import com.StartUp.StudyW.dto.response.FlashcardResponse;
import com.StartUp.StudyW.entity.Flashcard;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlashcardMapper
{
    Flashcard toFlashcard(FlashcardCreationRequest request);
    void updateFlashcard(@MappingTarget //Map data từ FlashcardUpdateRequest vào Flashcard
                         Flashcard flashcard, FlashcardUpdateRequest request);
    FlashcardResponse toFlashcardResponse(Flashcard flashcard);
}
