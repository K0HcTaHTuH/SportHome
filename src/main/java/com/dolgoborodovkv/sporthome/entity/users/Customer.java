package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.gym.OfferedService;
import com.dolgoborodovkv.sporthome.entity.gym.Opinion;
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
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Opinion> opinions = new HashSet<>();

    @ManyToMany(mappedBy = "customers")
    @Builder.Default
    private Set<OfferedService> offeredServices = new HashSet<>();

    public void addOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.add(opinion);
        opinion.setAuthor(this);
    }

    public void removeOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.remove(opinion);
        opinion.setAuthor(null);
    }
}
