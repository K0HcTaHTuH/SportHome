package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import com.dolgoborodovkv.sporthome.entity.users.User;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "postal_code")
    private Byte postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private Integer houseNumber;

    @Column(name = "entrance_number")
    private Byte entranceNumber;

    @Column(name = "floor")

    private Byte floor;

    @Column(name = "apartment")
    private Integer apartment;

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private Set<Gym> gyms = new HashSet<>();

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
