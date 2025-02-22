package br.com.nlw.events.mappers;

import br.com.nlw.events.dto.EventDto;
import br.com.nlw.events.entity.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventEntity toModel(EventDto eventDto);
    EventDto toDto(EventEntity eventEntity);

    List<EventDto> listEventDto(List<EventEntity> events);
}
