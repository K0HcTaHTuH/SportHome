package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.Address;
import com.dolgoborodovkv.sporthome.entity.BankAccount;
import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.users.Owner;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"imageUris", "addresses", "services", "records"})
@EqualsAndHashCode
@Entity
@Table(name = "gyms")
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private TimePeriod timePeriod;

    @Column(name = "business_phone")
    private Integer businessPhone;

    @ElementCollection
    @CollectionTable(
            name = "image_uris",
            joinColumns = @JoinColumn(name = "OWNER_ID")
    )
    @Builder.Default
    private Set<String> imageUris = new HashSet<>();//TODO сделать интеграцию с серивисом хранения фотографий

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Opinion> opinions = new HashSet<>();

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public void addRoom(@NonNull @Nonnull Room room) {
        rooms.add(room);
        room.setGym(this);
    }

    public void removeRoom(@NonNull @Nonnull Room room) {
        rooms.remove(room);
        room.setGym(null);
    }

    public void addOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.add(opinion);
        opinion.setGym(this);
    }

    public void removeOpinion(@NonNull @Nonnull Opinion opinion) {
        opinions.remove(opinion);
        opinion.setGym(null);
    }

    public void addBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setGym(this);
    }

    public void removeBankAccount(@NonNull @Nonnull BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
        bankAccount.setGym(null);
    }
}
