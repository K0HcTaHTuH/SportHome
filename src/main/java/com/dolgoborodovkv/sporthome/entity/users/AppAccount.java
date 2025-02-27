package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.BankAccount;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "app_accounts")
public class AppAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
