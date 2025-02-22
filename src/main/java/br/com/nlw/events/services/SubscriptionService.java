package br.com.nlw.events.services;

import br.com.nlw.events.dto.*;
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

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final EventRepository eventRepository;

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public SubscriptionResponseDto createNewSubscription(String eventName, UserDto user, Integer userId) {
        EventEntity event = eventRepository.findByPrettyName(eventName).orElseThrow(() -> {
            throw new CustomException("Evento não cadastrado", HttpStatus.NOT_FOUND, null);
        });

        UserEntity newUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (newUser == null) {
            newUser = userMapper.toModel(user);
            newUser = userRepository.save(newUser);
        }

        UserEntity indicador = userRepository.findById(userId).orElse(null);
        if (indicador == null) {
            throw new CustomException("Usuario " + userId + " indicador não existe", HttpStatus.BAD_REQUEST, null);
        }

        SubscriptionDto subs = new SubscriptionDto();
        subs.setEvent(event);
        subs.setUser(newUser);
        subs.setIndication(indicador);

        if (subscriptionRepository.findByEventAndUser(event, newUser).isPresent()) {
            throw new CustomException("Usuário já inscrito no evento: " + event.getPrettyName(), HttpStatus.BAD_REQUEST, null);
        }

        SubscriptionEntity newSubs = subscriptionMapper.toModel(subs);
        subscriptionRepository.save(newSubs);

        return new SubscriptionResponseDto(newSubs.getSubscriptionNumber(), "http://codecraft.com/subscription/"+newSubs.getEvent().getPrettyName()+"/"+newSubs.getUser().getId());
    }

    public List<SubscriptionRankingItemDto> getCompleteRanking(String prettyName) {
        EventEntity event = eventRepository.findByPrettyName(prettyName).orElseThrow(() -> {
            throw new CustomException("Ranking do evento: " + prettyName + " não existe", HttpStatus.NOT_FOUND, null);
        });

        List<Object[]> rankingData = subscriptionRepository.generateRanking(event.getEventId());
        return subscriptionMapper.toRankingDtoList(rankingData);
    }

    public SubscriptionRakingByUser getRankingByUser(String prettyName, Integer userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new CustomException("Usuário " + userId + " não foi cadastrado no sistema", HttpStatus.BAD_REQUEST, null);
        }

        List<SubscriptionRankingItemDto> ranking = getCompleteRanking(prettyName);

        SubscriptionRankingItemDto item = ranking.stream().filter(i->i.userId().equals(userId)).findFirst().orElse(null);
        if (item == null) {
            throw new CustomException("Não há inscrições para este usuario " + userId, HttpStatus.NOT_FOUND, null);
        }

        Integer posicao = IntStream.range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst().getAsInt();
        return new SubscriptionRakingByUser(item, posicao+1);
    }
}
