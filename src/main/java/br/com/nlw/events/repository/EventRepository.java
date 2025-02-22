package br.com.nlw.events.repository;

import br.com.nlw.events.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    Optional<EventEntity> findByPrettyName(String prettyName);
}