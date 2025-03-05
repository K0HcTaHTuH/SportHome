package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.enums.OfferedServiceStatus;
import com.dolgoborodovkv.sporthome.entity.users.Coach;
import com.dolgoborodovkv.sporthome.entity.users.Customer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность предлогаемой пользователю услуги.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"rooms", "customers"})
@EqualsAndHashCode(exclude = {"rooms", "customers"})
@Entity
@Table(name = "offered_services")
public class OfferedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Цена предлагаемой услуги.
     */
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * Длительность тренировки.
     */
    @Column(name = "duration")
    private Duration duration;

    /**
     * Временной интервал предлагаемой тренировки.
     */
    @Embedded
    private TimePeriod timePeriod;

    /**
     * Статус предоставления услуги.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OfferedServiceStatus offeredServiceStatus;

    /**
     * Список комнат в которых предлагаются услуги.
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "offered_service_rooms",
            joinColumns = @JoinColumn(name = "offered_service_id"),
            inverseJoinColumns = @JoinColumn(name = "rooms_id")
    )
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();

    /**
     * Услуга выбранная клиентом.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fitness_service_id")
    private FitnessService fitnessService;

    /**
     * Тренер доступный для проведения тренеровки.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "coach_id")
    private Coach coach;

    /**
     * Список записавшихся клиентов.
     */
    @ManyToMany(mappedBy = "offeredServices")
    @Builder.Default
    private Set<Customer> customers = new HashSet<>();

    public void addRoom(@NonNull @Nonnull Room room) {
        rooms.add(room);
        room.getOfferedServices().add(this);
    }

    public void removeRoom(@NonNull @Nonnull Room room) {
        rooms.remove(room);
        room.getOfferedServices().remove(this);
    }
}
