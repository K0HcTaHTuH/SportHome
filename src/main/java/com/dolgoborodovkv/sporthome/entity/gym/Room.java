package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.enums.RoomStatus;
import com.dolgoborodovkv.sporthome.entity.enums.TimePeriodStatus;
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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RoomStatus roomStatus = RoomStatus.POSSIBLE;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @ElementCollection(targetClass = TimePeriod.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "working_hours", joinColumns = @JoinColumn(name = "services_id"))
    @Builder.Default
    private Set<TimePeriod> trainingHours = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();

    public void addWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        trainingHours.add(timePeriod);
    }

    public void removeWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        trainingHours.remove(timePeriod);
    }

    public void addServices(@NonNull @Nonnull Service service) {
        services.add(service);
        service.setRoom(this);
    }
    public void removeServices(@NonNull @Nonnull Service service) {
        services.remove(service);
        service.setRoom(null);
    }

    public void addOfferedServices(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.add(offeredService);
        offeredService.setRoom(this);
    }
    public void removeOfferedServices(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.remove(offeredService);
        offeredService.setRoom(null);
    }
}
