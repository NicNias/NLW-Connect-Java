package br.com.nlw.events.mappers;

import br.com.nlw.events.dto.SubscriptionDto;
import br.com.nlw.events.dto.SubscriptionRankingItemDto;
import br.com.nlw.events.entity.SubscriptionEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionEntity toModel(SubscriptionDto subscriptionDto);
    SubscriptionDto toDto(SubscriptionEntity subscriptionEntity);

    List<SubscriptionDto> listSubsDto(List<SubscriptionEntity> subs);

    default SubscriptionRankingItemDto toRankingItemDto(Object[] rankingRow) {
        if (rankingRow == null || rankingRow.length < 3) {
            return null;
        }
        return new SubscriptionRankingItemDto(
                ((Number) rankingRow[0]).longValue(),
                ((Number) rankingRow[1]).intValue(),
                (String) rankingRow[2]
        );
    }

    default List<SubscriptionRankingItemDto> toRankingDtoList(List<Object[]> rankingData) {
        return rankingData.stream()
                .map(this::toRankingItemDto)
                .collect(Collectors.toList());
    }
}
