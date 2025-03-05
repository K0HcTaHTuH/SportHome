package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import com.dolgoborodovkv.sporthome.entity.users.AppAccount;
import jakarta.persistence.*;
import lombok.*;

/**
 * Денежные переводы для покупки поинтов и их переводов за услуги, а так же сбора комиссии с платежей.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Аккаунт сбора комиссии за покупку поинтов.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private AppAccount appAccount;

    /**
     * Зал получающий оплату за предоставленную услугу.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Gym gym;

    //TODO дописать поля для банковских переводов
}
