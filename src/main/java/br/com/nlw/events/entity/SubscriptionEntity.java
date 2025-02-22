package br.com.nlw.events.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_subscription")
@Getter @Setter
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Integer subscriptionNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "indication_user_id", nullable = true)
    private UserEntity indication;
}
