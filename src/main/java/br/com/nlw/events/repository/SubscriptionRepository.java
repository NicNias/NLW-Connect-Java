package br.com.nlw.events.repository;

import br.com.nlw.events.entity.EventEntity;
import br.com.nlw.events.entity.SubscriptionEntity;
import br.com.nlw.events.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    Optional<SubscriptionEntity> findByEventAndUser(EventEntity event, UserEntity user);

    @Query(value = """
        SELECT COUNT(subscription_number) AS quantidade, 
               indication_user_id, 
               user_name
        FROM tbl_subscription 
        INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id 
        WHERE indication_user_id IS NOT NULL
          AND event_id = :eventId
        GROUP BY indication_user_id
        ORDER BY quantidade DESC
        """, nativeQuery = true)
    List<Object[]> generateRanking(@Param("eventId") Integer eventId);
}
