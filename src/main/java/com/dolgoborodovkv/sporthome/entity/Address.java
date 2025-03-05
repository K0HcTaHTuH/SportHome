package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import com.dolgoborodovkv.sporthome.entity.users.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Адрес пользователя и спортивного зала.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "gyms")
@EqualsAndHashCode(exclude = "gyms")
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Почтовый код региона проживания или временного места нахождения.
     */
    @Column(name = "postal_code")
    private Byte postalCode;

    /**
     * Страна проживания или временного места нахождения.
     */
    @Column(name = "country")
    private String country;

    /**
     * Город проживания или временного места нахождения.
     */
    @Column(name = "city")
    private String city;

    /**
     * Улица проживания или временного места нахождения.
     */
    @Column(name = "street")
    private String street;

    /**
     * Номер дома проживания или временного места нахождения.
     */
    @Column(name = "house_number")
    private Integer houseNumber;

    /**
     * Номер подьезда.
     */
    @Column(name = "entrance_number")
    private Integer entranceNumber;

    /**
     * Номер этажа.
     */
    @Column(name = "floor")
    private Integer floor;

    /**
     * Номер квартиры
     */
    @Column(name = "apartment")
    private Integer apartment;

    /**
     * Список спортивных залов которым присваивается адрес.
     */
    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private Set<Gym> gyms = new HashSet<>();

    /**
     * Пользователь которому присваивается адрес.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    public void addGym(@NonNull @Nonnull Gym gym) {
        gyms.add(gym);
        gym.setAddress(this);
    }

    public void removeGym(@NonNull @Nonnull Gym gym) {
        gyms.remove(gym);
        gym.setAddress(null);
    }
}
