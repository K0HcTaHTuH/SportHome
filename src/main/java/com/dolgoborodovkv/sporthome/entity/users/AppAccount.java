package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.BankAccount;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для сбора и хранения комиссии за покупку поинтов и других услуг.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"bankAccounts"})
@EqualsAndHashCode(exclude = {"bankAccounts"})
@Entity
@Table(name = "app_accounts")
public class AppAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Список банковских аккаунтов с которых поступает комиссия.
     */
    @OneToMany(mappedBy = "appAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public void addBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setAppAccount(this);
    }

    public void removeBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
        bankAccount.setAppAccount(null);
    }
}
