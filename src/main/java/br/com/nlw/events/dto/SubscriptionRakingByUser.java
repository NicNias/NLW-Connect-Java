package br.com.nlw.events.dto;

public record SubscriptionRakingByUser(
        SubscriptionRankingItemDto item,
        Integer position
) {
}
