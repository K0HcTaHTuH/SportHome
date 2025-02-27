package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.enums.UserCountType;
import com.dolgoborodovkv.sporthome.entity.users.Couch;
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
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "user_count_type")
    @Enumerated(EnumType.STRING)
    private UserCountType userCountType;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany(mappedBy = "services")
    @Builder.Default
    private Set<Couch> couches = new HashSet<>();

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();


    public void addOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.add(offeredService);
        offeredService.setService(this);
    }

    public void removeOfferedService(@NonNull @Nonnull OfferedService offeredService) {
        offeredServices.remove(offeredService);
        offeredService.setService(null);
    }
}
