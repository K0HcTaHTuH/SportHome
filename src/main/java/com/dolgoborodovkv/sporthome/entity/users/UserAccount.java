package com.dolgoborodovkv.sporthome.entity.users;

import jakarta.persistence.*;
import lombok.*;

/**
 * Кошелек пользователя хранящий баланс поинтов.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * Баланс поинтов за которые пользователь может приобритать услуги.
     */
    @Column(name = "point_balance")
    private Integer pointBalance;

    /**
     * Пользователь - владелец кошелька.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;
}

