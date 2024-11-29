package com.StartUp.StudyW.mapper;

import com.StartUp.StudyW.dto.request.FlashcardCreationRequest;
import com.StartUp.StudyW.dto.request.FlashcardUpdateRequest;
import com.StartUp.StudyW.dto.response.FlashcardResponse;
import com.StartUp.StudyW.entity.Flashcard;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class FlashcardMapperImpl implements FlashcardMapper {

    @Override
    public Flashcard toFlashcard(FlashcardCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Flashcard.FlashcardBuilder flashcard = Flashcard.builder();

        flashcard.id( request.getId() );
        flashcard.question( request.getQuestion() );
        flashcard.answer( request.getAnswer() );
        flashcard.deck( request.getDeck() );

        return flashcard.build();
    }

    @Override
    public void updateFlashcard(Flashcard flashcard, FlashcardUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        flashcard.setQuestion( request.getQuestion() );
        flashcard.setAnswer( request.getAnswer() );
        flashcard.setDeck( request.getDeck() );
    }

    @Override
    public FlashcardResponse toFlashcardResponse(Flashcard flashcard) {
        if ( flashcard == null ) {
            return null;
        }

        FlashcardResponse.FlashcardResponseBuilder flashcardResponse = FlashcardResponse.builder();

        flashcardResponse.id( flashcard.getId() );
        flashcardResponse.question( flashcard.getQuestion() );
        flashcardResponse.answer( flashcard.getAnswer() );
        flashcardResponse.deck( flashcard.getDeck() );

        return flashcardResponse.build();
    }
}
