package br.com.nlw.events.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", length = 255, nullable = false)
    private String name;

    @Column(name = "user_email", length = 255, nullable = false, unique = true) @Email
    private String email;
}
