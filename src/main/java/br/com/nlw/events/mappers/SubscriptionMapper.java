package br.com.nlw.events.mappers;

import br.com.nlw.events.dto.SubscriptionDto;
import br.com.nlw.events.entity.SubscriptionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionEntity toModel(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(SubscriptionEntity subscriptionEntity);

    List<SubscriptionDto> listSubsDto(List<SubscriptionEntity> subs);
}
