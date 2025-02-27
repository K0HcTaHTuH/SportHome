package com.dolgoborodovkv.sporthome.entity.users;

import jakarta.persistence.*;
import lombok.*;

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
     * баланс поинтов за которые пользователь может приобритать услуги
     */
    @Column(name = "point_balance")
    private Integer pointBalance;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;
}

