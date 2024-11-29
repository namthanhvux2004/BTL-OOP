package com.StartUp.StudyW.Service;


import com.StartUp.StudyW.dto.request.FlashcardCreationRequest;
import com.StartUp.StudyW.dto.request.FlashcardUpdateRequest;
import com.StartUp.StudyW.dto.response.FlashcardResponse;
import com.StartUp.StudyW.entity.Flashcard;
import com.StartUp.StudyW.exception.AppException;
import com.StartUp.StudyW.exception.ErrorCode;
import com.StartUp.StudyW.mapper.FlashcardMapper;
import com.StartUp.StudyW.repository.FlashcardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@RequiredArgsConstructor
public class FlashcardService
{
    FlashcardRepository flashcardRepository;
    FlashcardMapper flashcardMapper;

    public Flashcard createFlashcard(FlashcardCreationRequest request)
    {
        if (flashcardRepository.existsByQuestion(request.getQuestion()))
            throw new AppException(ErrorCode.DECK_EXISTED);

        Flashcard flashcard = flashcardMapper.toFlashcard(request);

        return flashcardRepository.save(flashcard);
    }

    public List<FlashcardResponse> getFlashcards()
    {
        return flashcardRepository.findAll().stream()
                .map(flashcardMapper::toFlashcardResponse).toList();
    }

    public FlashcardResponse getFlashcard(String id)
    {
        return flashcardMapper.toFlashcardResponse(flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found!")));
    }

    public FlashcardResponse updateFlashcard(String flashcardId, FlashcardUpdateRequest request)
    {
        Flashcard flashcard =flashcardRepository.findById(flashcardId)
                .orElseThrow(()-> new RuntimeException("Flashcard not found!"));

        flashcardMapper.updateFlashcard(flashcard, request);

        return flashcardMapper.toFlashcardResponse(flashcardRepository.save(flashcard));
    }

    public void deleteFlashcard(String flashcardId)
    {
        flashcardRepository.deleteById(flashcardId);
    }

    public List<FlashcardResponse> getFlashcardsByDeckId(String deckId) {
        // Truy vấn flashcards từ database theo deckId
        List<Flashcard> flashcards = flashcardRepository.findByDeckId(deckId);

        // Chuyển đổi danh sách Flashcard thành FlashcardResponse
        return flashcards.stream()
                .map(flashcard -> FlashcardResponse.builder()
                        .id(flashcard.getId())
                        .question(flashcard.getQuestion())
                        .answer(flashcard.getAnswer())
                        .deck(flashcard.getDeck()) // Bao gồm cả Deck nếu cần thiết
                        .build())
                .collect(Collectors.toList());
    }

}

