package br.com.nlw.events.services;

import br.com.nlw.events.dto.SubscriptionDto;
import br.com.nlw.events.dto.UserDto;
import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import br.com.nlw.events.exceptions.CustomException;
import br.com.nlw.events.mappers.SubscriptionMapper;
import br.com.nlw.events.mappers.UserMapper;
import br.com.nlw.events.repository.EventRepository;
import br.com.nlw.events.repository.SubscriptionRepository;
import br.com.nlw.events.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserMapper userMapper;

    public SubscriptionDto createNewSubscription(String eventName, UserDto user) {
        EventEntity event = eventRepository.findByPrettyName(eventName).orElseThrow(() -> {
            throw new CustomException("Evento não cadastrado", HttpStatus.NOT_FOUND, null);
        });

        UserEntity newUser = userRepository.findByEmail(user.getEmail()).orElse(null);

        if (newUser == null) {
            newUser = userMapper.toModel(user);
            newUser = userRepository.save(newUser);
        }

        SubscriptionDto subs = new SubscriptionDto();
        subs.setEvent(event);
        subs.setUser(newUser);

        SubscriptionEntity newSubs = subscriptionMapper.toModel(subs);
        subscriptionRepository.save(newSubs);
        return subscriptionMapper.toDto(newSubs);
    }
}
