package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.enums.RoomStatus;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Комната в которой возможно или невозможно оказание услуг.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"trainingHours", "services", "offeredServices"})
@EqualsAndHashCode(exclude = {"trainingHours", "services", "offeredServices"})
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Статус доступности комнаты для оказываемых услуг.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoomStatus roomStatus = RoomStatus.FREE;

    /**
     * Фитнес зал к которому относится комната.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "gym_id")
    private Gym gym;

    /**
     * Список временных интервалов в которые возможно оказать услугу.
     */
    @ElementCollection(targetClass = TimePeriod.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "acceptable_period", joinColumns = @JoinColumn(name = "room_id"))
    @Builder.Default
    private Set<TimePeriod> acceptableTrainingPeriod = new HashSet<>();

    /**
     * Список допустмых в комнате услуг.
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "room_fitness_services",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "fitness_services_id")
    )
    @Builder.Default
    private Set<FitnessService> fitnessServices = new HashSet<>();

    /**
     * Список предлагаемых услуг для которых выбрана комната.
     */
    @ManyToMany(mappedBy = "rooms")
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();

    public void addTimePeriod(@NonNull @Nonnull TimePeriod timePeriod) {
        acceptableTrainingPeriod.add(timePeriod);
    }

    public void removeTimePeriod(@NonNull @Nonnull TimePeriod timePeriod) {
        acceptableTrainingPeriod.remove(timePeriod);
    }

    public void addFitnessService(@NonNull @Nonnull FitnessService fitnessService) {
        fitnessServices.add(fitnessService);
        fitnessService.getRooms().add(this);
    }

    public void removeFitnessService(@NonNull @Nonnull FitnessService fitnessService) {
        fitnessServices.remove(fitnessService);
        fitnessService.getRooms().remove(this);
    }
}
