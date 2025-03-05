package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.gym.OfferedService;
import com.dolgoborodovkv.sporthome.entity.gym.Opinion;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с пользователем который выбрал роль "Customer".
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"opinions", "offeredServices"})
@EqualsAndHashCode(exclude = {"opinions", "offeredServices"})
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * Обьект основного класса для работы с пользователем.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Кошелек клиента который хранит баланс поинтов - общей валюты.
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private UserAccount userAccount;

    /**
     * Список отзывов которые оставил клиент.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Opinion> opinions = new HashSet<>();

    /**
     * Список предлагаемых клиенту услуг.
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "customer_offered_services",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "offered_services_id")
    )
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();

    public void addOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.add(opinion);
        opinion.setAuthor(this);
    }

    public void removeOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.remove(opinion);
        opinion.setAuthor(null);
    }

    public void addOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.add(offeredService);
        offeredService.getCustomers().add(this);
    }

    public void removeOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.remove(offeredService);
        offeredService.getCustomers().remove(this);
    }
}
