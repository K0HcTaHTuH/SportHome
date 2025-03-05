package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс для работы с пользователем который выбрал роль "Owner".
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "gyms")
@EqualsAndHashCode(exclude = "gyms")
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @Column(name = "user_id")
    private Long userId;

    /**
     * Обьект основного класса для работы с пользователем
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    /**
     * Список фитнес залов владельца.
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Gym> gyms = new HashSet<>();

    public void addGym(@NonNull @Nonnull Gym gym) {
        gyms.add(gym);
        gym.setOwner(this);
    }

    public void removeGym(@NonNull @Nonnull Gym gym) {
        gyms.remove(gym);
        gym.setOwner(null);
    }
}
