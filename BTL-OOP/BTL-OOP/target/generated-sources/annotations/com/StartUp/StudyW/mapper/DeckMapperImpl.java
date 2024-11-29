package com.StartUp.StudyW.mapper;

import com.StartUp.StudyW.dto.request.DeckCreationRequest;
import com.StartUp.StudyW.dto.request.DeckUpdateRequest;
import com.StartUp.StudyW.dto.response.DeckResponse;
import com.StartUp.StudyW.entity.Deck;
import com.StartUp.StudyW.entity.Flashcard;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class DeckMapperImpl implements DeckMapper {

    @Override
    public Deck toDeck(DeckCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Deck.DeckBuilder deck = Deck.builder();

        deck.id( request.getId() );
        deck.name( request.getName() );
        deck.description( request.getDescription() );
        Set<Flashcard> set = request.getFlashcards();
        if ( set != null ) {
            deck.flashcards( new LinkedHashSet<Flashcard>( set ) );
        }

        return deck.build();
    }

    @Override
    public void updateDeck(Deck deck, DeckUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        deck.setName( request.getName() );
        deck.setDescription( request.getDescription() );
        if ( deck.getFlashcards() != null ) {
            Set<Flashcard> set = request.getFlashcards();
            if ( set != null ) {
                deck.getFlashcards().clear();
                deck.getFlashcards().addAll( set );
            }
            else {
                deck.setFlashcards( null );
            }
        }
        else {
            Set<Flashcard> set = request.getFlashcards();
            if ( set != null ) {
                deck.setFlashcards( new LinkedHashSet<Flashcard>( set ) );
            }
        }
    }

    @Override
    public DeckResponse toDeckResponse(Deck deck) {
        if ( deck == null ) {
            return null;
        }

        DeckResponse.DeckResponseBuilder deckResponse = DeckResponse.builder();

        deckResponse.id( deck.getId() );
        deckResponse.name( deck.getName() );
        deckResponse.description( deck.getDescription() );
        Set<Flashcard> set = deck.getFlashcards();
        if ( set != null ) {
            deckResponse.flashcards( new LinkedHashSet<Flashcard>( set ) );
        }

        return deckResponse.build();
    }
}
