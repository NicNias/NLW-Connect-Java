package br.com.nlw.events.dto;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Subscription {
    private EventEntity event;
    private UserEntity user;
    private UserEntity indication;
}
