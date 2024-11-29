package com.StartUp.StudyW.controller;

import com.StartUp.StudyW.Service.DeckService;
import com.StartUp.StudyW.dto.request.ApiResponse;
import com.StartUp.StudyW.dto.request.DeckCreationRequest;
import com.StartUp.StudyW.dto.request.DeckUpdateRequest;
import com.StartUp.StudyW.dto.response.DeckResponse;
import com.StartUp.StudyW.entity.Deck;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
public class DeckController
{
    DeckService deckService;

    @PostMapping
    ApiResponse<Deck> createDeck(@RequestBody DeckCreationRequest request) {
        ApiResponse<Deck> apiResponse = new ApiResponse<>();

        // Tạo Deck và Flashcards đồng thời
        apiResponse.setResult(deckService.createDeck(request, request.getFlashcards()));

        return apiResponse;
    }

    @PostMapping("/{deckId}/flashcards")
    public void addFlashcardsToDeck(@PathVariable String deckId,
                                    @RequestBody List<String> flashcardIds) {
        deckService.addFlashcardsToDeck(deckId, flashcardIds);
    }

    @GetMapping
    List<DeckResponse> getDecks()
    {
        return deckService.getDecks();
    }

    @GetMapping("/{deckId}")
    DeckResponse getDeck(@PathVariable("deckId") String deckId)
    {
        return deckService.getDeck(deckId);
    }

    @GetMapping("/search")
    public List<Deck> searchDecks(@RequestParam String query) {
        return deckService.searchDecks(query);
    }


    @PutMapping("{deckId}")
    DeckResponse updateDeck(@PathVariable String deckId, DeckUpdateRequest request)
    {
        return deckService.updateDeck(deckId, request);
    }

    @DeleteMapping("{deckId}")
    String deleteDeck(@PathVariable String deckId)
    {
        deckService.deleteDeck(deckId);
        return "Deck has been deleted!";
    }

    // Xóa Flashcard khỏi Deck
    @DeleteMapping("/{deckId}/flashcards/{flashcardId}")
    public void removeFlashcardFromDeck(@PathVariable String deckId,
                                        @PathVariable String flashcardId) {
        deckService.removeFlashcardFromDeck(deckId, flashcardId);
    }
}

