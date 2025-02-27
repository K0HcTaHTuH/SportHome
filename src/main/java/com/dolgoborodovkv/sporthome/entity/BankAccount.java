package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import com.dolgoborodovkv.sporthome.entity.users.AppAccount;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppAccount appAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Gym gym;

    //TODO дописать поля для банковских переводов
}
