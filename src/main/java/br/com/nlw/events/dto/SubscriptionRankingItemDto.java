package br.com.nlw.events.dto;

public record SubscriptionRankingItemDto(
        Long subscribers,
        Integer userId,
        String name
) {
}
