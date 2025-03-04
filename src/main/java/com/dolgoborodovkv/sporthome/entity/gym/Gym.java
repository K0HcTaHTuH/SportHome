package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.Address;
import com.dolgoborodovkv.sporthome.entity.BankAccount;
import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.users.Owner;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность для регистрации спортивного зала.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"imageUris", "rooms", "opinions", "bankAccounts"})
@EqualsAndHashCode(exclude = {"imageUris", "rooms", "opinions", "bankAccounts"})
@Entity
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Название спортивного зала.
     */
    @Column(name = "name")
    private String name;

    /**
     * Описание спортивного зала.
     */
    @Column(name = "description")
    private String description;

    /**
     * Временной период работы спортивного зала.
     */
    @Embedded
    private TimePeriod timePeriod;

    /**
     * Корпоративный телефон для связи с клиентами.
     */
    @Column(name = "business_phone")
    private Integer businessPhone;

    /**
     * Владелец спортивного зала.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    /**
     * Адрес спортивного зала.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    /**
     * Фотографии спортивного зала для предоставления пользователям.
     */
    @ElementCollection
    @CollectionTable(
            name = "image_uris",
            joinColumns = @JoinColumn(name = "OWNER_ID")
    )
    @Builder.Default
    private Set<String> imageUris = new HashSet<>();//TODO сделать интеграцию с серивисом хранения фотографий

    /**
     * Список комнат спортивного зала.
     */
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();

    /**
     * Список отзывов оставленных клиентами.
     */
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Opinion> opinions = new HashSet<>();

    /**
     * Список банковских аккаунтов для оплаты услуг.
     */
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public void addRoom(@NonNull @Nonnull Room room) {
        rooms.add(room);
        room.setGym(this);
    }

    public void removeRoom(@NonNull @Nonnull Room room) {
        rooms.remove(room);
        room.setGym(null);
    }

    public void addOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.add(opinion);
        opinion.setGym(this);
    }

    public void removeOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.remove(opinion);
        opinion.setGym(null);
    }

    public void addBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setGym(this);
    }

    public void removeBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
        bankAccount.setGym(null);
    }
}
