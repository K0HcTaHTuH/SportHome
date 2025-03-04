package com.dolgoborodovkv.sporthome.entity.users;


import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.gym.FitnessService;
import com.dolgoborodovkv.sporthome.entity.gym.OfferedService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с пользователем который выбрал роль "Coach".
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"trainingRegistrations", "fitnessServices", "workingHours"})
@EqualsAndHashCode(exclude = {"trainingRegistrations", "fitnessServices", "workingHours"})
@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * Ссылка на профиль в социальных сетях или резюме
     */
    @Column(name = "link_to_resume")
    private String linkToResume;

    /**
     * Обьект основного класса для работы с пользователем.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Список предлагаемых услуг на которые записывается клиент.
     */
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OfferedService> trainingRegistrations = new HashSet<>();

    /**
     * Список услуг которые оказывает тренер.
     */
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "coach_services",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "fitness_services_id")
    )
    @Builder.Default
    private Set<FitnessService> fitnessServices = new HashSet<>();

    /**
     * Список временных интервалов в которые может работать тренер.
     */
    @ElementCollection(targetClass = TimePeriod.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "working_hours", joinColumns = @JoinColumn(name = "user_id"))
    @Builder.Default
    private Set<TimePeriod> workingHours = new HashSet<>();

    public void addTrainingRegistration(@NonNull @Nonnull OfferedService trainingRegistration) {
        trainingRegistrations.add(trainingRegistration);
        trainingRegistration.setCoach(this);
    }

    public void removeTrainingRegistration(@NonNull @Nonnull OfferedService appointment) {
        trainingRegistrations.remove(appointment);
        appointment.setCoach(null);
    }

    public void addService(@NonNull @Nonnull FitnessService fitnessService) {
        fitnessServices.add(fitnessService);
        fitnessService.getCoaches().add(this);
    }

    public void removeService(@NonNull @Nonnull FitnessService fitnessService) {
        fitnessServices.remove(fitnessService);
        fitnessService.getCoaches().remove(this);
    }

    public void addWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        workingHours.add(timePeriod);
    }

    public void removeWorkingHours(@NonNull @Nonnull TimePeriod timePeriod) {
        workingHours.remove(timePeriod);
    }
}
