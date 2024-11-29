package com.StartUp.StudyW.controller;

import com.StartUp.StudyW.Service.FlashcardService;
import com.StartUp.StudyW.dto.request.ApiResponse;
import com.StartUp.StudyW.dto.request.FlashcardCreationRequest;
import com.StartUp.StudyW.dto.request.FlashcardUpdateRequest;
import com.StartUp.StudyW.dto.response.FlashcardResponse;
import com.StartUp.StudyW.entity.Flashcard;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/flashcards")
@RequiredArgsConstructor
public class FlashcardController
{
    FlashcardService flashcardService;

    @PostMapping
    ApiResponse<Flashcard> createFlashcard(@RequestBody FlashcardCreationRequest request)
    {
        ApiResponse<Flashcard> apiResponse = new ApiResponse<>();

        apiResponse.setResult(flashcardService.createFlashcard(request));

        return apiResponse;
    }

    @GetMapping
    List<FlashcardResponse> getFlashcards()
    {
        return flashcardService.getFlashcards();
    }

    @GetMapping("/{flashcardId}")
    FlashcardResponse getFlashcard(@PathVariable("flashcardId") String flashcardId)
    {
        return flashcardService.getFlashcard(flashcardId);
    }

    // API lấy flashcards theo deckId
    @GetMapping("/deck/{deckId}")
    public List<FlashcardResponse> getFlashcardsByDeckId(@PathVariable("deckId") String deckId) {
        return flashcardService.getFlashcardsByDeckId(deckId);
    }


    @PutMapping("{flashcardId}")
    FlashcardResponse updateFlashcard(@PathVariable String flashcardId, FlashcardUpdateRequest request)
    {
        return flashcardService.updateFlashcard(flashcardId, request);
    }

    @DeleteMapping("{flashcardId}")
    String deleteFlashcard(@PathVariable String flashcardId)
    {
        flashcardService.deleteFlashcard(flashcardId);
        return "Flashcard has been deleted!";
    }
}
