package com.StartUp.StudyW.mapper;


import com.StartUp.StudyW.dto.request.DeckCreationRequest;
import com.StartUp.StudyW.dto.request.DeckUpdateRequest;
import com.StartUp.StudyW.dto.response.DeckResponse;
import com.StartUp.StudyW.entity.Deck;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeckMapper
{
    Deck toDeck(DeckCreationRequest request);
    void updateDeck(@MappingTarget //Map data từ DeckUpdateRequest vào Deck
                         Deck deck, DeckUpdateRequest request);
    DeckResponse toDeckResponse(Deck deck);
}