package com.StartUp.StudyW.Service;

import com.StartUp.StudyW.dto.request.DeckCreationRequest;
import com.StartUp.StudyW.dto.request.DeckUpdateRequest;
import com.StartUp.StudyW.dto.response.DeckResponse;
import com.StartUp.StudyW.entity.Deck;
import com.StartUp.StudyW.entity.Flashcard;
import com.StartUp.StudyW.exception.AppException;
import com.StartUp.StudyW.exception.ErrorCode;
import com.StartUp.StudyW.mapper.DeckMapper;
import com.StartUp.StudyW.repository.DeckRepository;
import com.StartUp.StudyW.repository.FlashcardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@RequiredArgsConstructor
public class DeckService
{
    DeckRepository deckRepository;
    DeckMapper deckMapper;

    FlashcardRepository flashcardRepository;

    public Deck createDeck(DeckCreationRequest request, Set<Flashcard> flashcards) {
        // Kiểm tra xem Deck có tên giống đã tồn tại hay không
        if (deckRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.DECK_EXISTED);


        // Tạo Deck mới
        Deck deck = new Deck();
        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        // Nếu có flashcards, liên kết chúng với Deck
        if (flashcards != null && !flashcards.isEmpty()) {
            flashcards.forEach(flashcard -> flashcard.setDeck(deck));  // Gán deck cho từng flashcard
            deck.setFlashcards(flashcards);  // Gán danh sách flashcards vào deck
        }

        // Lưu Deck vào database và trả về
        return deckRepository.save(deck);
    }

    public List<DeckResponse> getDecks()
    {
        return deckRepository.findAll().stream()
                .map(deckMapper::toDeckResponse).toList();
    }

    public DeckResponse getDeck(String id)
    {
        return deckMapper.toDeckResponse(deckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deck not found!")));
    }

    public DeckResponse updateDeck(String deckId, DeckUpdateRequest request)
    {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(()-> new RuntimeException("Deck not found!"));

        deckMapper.updateDeck(deck, request);

        return deckMapper.toDeckResponse(deckRepository.save(deck));
    }

    public void deleteDeck(String deckId)
    {
        deckRepository.deleteById(deckId);
    }

    public void removeFlashcardFromDeck(String deckId, String flashcardId) {
        // Tìm bộ đề theo ID
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found!"));

        // Tìm flashcard theo ID
        Flashcard flashcard = flashcardRepository.findById(flashcardId)
                .orElseThrow(() -> new RuntimeException("Flashcard not found!"));

        // Xóa flashcard khỏi bộ đề
        deck.getFlashcards().remove(flashcard);

        // Lưu lại bộ đề sau khi đã cập nhật
        deckRepository.save(deck);
    }

    public void addFlashcardsToDeck(String deckId, List<String> flashcardIds) {
        // Tìm bộ đề theo ID
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found!"));

        // Tìm các flashcard theo ID
        List<Flashcard> flashcards = flashcardRepository.findAllById(flashcardIds);

        // Thêm flashcards vào bộ đề
        deck.getFlashcards().addAll(flashcards);

        // Lưu lại bộ đề đã được cập nhật
        deckRepository.save(deck);
    }


    // Tìm kiếm bộ đề theo từ khóa (ví dụ: tìm theo tên hoặc mô tả)
    public List<Deck> searchDecks(String query) {
        return deckRepository.findByNameContainingOrDescriptionContaining(query, query);
    }


}

