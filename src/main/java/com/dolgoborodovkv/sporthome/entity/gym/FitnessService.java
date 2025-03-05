package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.enums.UserCountType;
import com.dolgoborodovkv.sporthome.entity.users.Coach;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность услуги которая будет предоставлена клиенту.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"rooms", "coaches", "offeredServices"})
@EqualsAndHashCode(exclude = {"rooms", "coaches", "offeredServices"})
@Entity
@Table(name = "fitness_services")
public class FitnessService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Название услуги.
     */
    @Column(name = "title")
    private String title;

    /**
     * Статус типа тренеровки.
     */
    @Column(name = "user_count_type")
    @Enumerated(EnumType.STRING)
    private UserCountType userCountType;

    /**
     * Описание услуги.
     */
    @Column(name = "description")
    private String description;

    /**
     * Список помещений в которых доступна услуга.
     */
    @ManyToMany(mappedBy = "fitnessServices")
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();

    /**
     * Список тренеров которые могут провести тренеровку.
     */
    @ManyToMany(mappedBy = "fitnessServices")
    @Builder.Default
    private Set<Coach> coaches = new HashSet<>();

    /**
     * Список предоставляемых услуг.
     */
    @OneToMany(mappedBy = "fitnessService", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();


    public void addOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.add(offeredService);
        offeredService.setFitnessService(this);
    }

    public void removeOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.remove(offeredService);
        offeredService.setFitnessService(null);
    }
}
