package com.dolgoborodovkv.sporthome.entity.users;


import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.gym.OfferedService;
import com.dolgoborodovkv.sporthome.entity.gym.Service;
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
@Table(name = "mentors")
public class Couch {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * Ссылка на профиль в социальных сетях или резюме
     */
    @Column(name = "link_to_resume")
    private String linkToResume;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToMany(mappedBy = "couch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OfferedService> trainingRegistrations = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "feasible_service",
            joinColumns = @JoinColumn(name = "couch_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @Builder.Default
    private Set<Service> services = new HashSet<>();

    @ElementCollection(targetClass = TimePeriod.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "working_hours", joinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private Set<TimePeriod> workingHours = new HashSet<>();

    public void addTrainingRegistration(@NonNull @Nonnull OfferedService trainingRegistration) {
        trainingRegistrations.add(trainingRegistration);
        trainingRegistration.setCouch(this);
    }

    public void removeTrainingRegistration(@NonNull @Nonnull OfferedService appointment) {
        trainingRegistrations.remove(appointment);
        appointment.setCouch(null);
    }

    public void addService(@NonNull @Nonnull Service service) {
        services.add(service);
        service.getCouches().add(this);
    }

    public void removeService(@NonNull @Nonnull Service service) {
        services.remove(service);
        service.getCouches().remove(this);
    }

    public void addWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        workingHours.add(timePeriod);
    }

    public void removeWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        workingHours.remove(timePeriod);
    }
}
