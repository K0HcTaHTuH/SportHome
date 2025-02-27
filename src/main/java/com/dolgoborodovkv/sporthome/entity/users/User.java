package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.Address;
import com.dolgoborodovkv.sporthome.entity.enums.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Couch couch;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Owner owner;

    @OneToMany(mappedBy = "couch", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    Set<Address> addresses = new HashSet<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> roles = new HashSet<>(Set.of(Role.REGISTERED));

    public void addRole(@NonNull @Nonnull Role role) {
        roles.add(role);
    }

    public void removeRole(@NonNull @Nonnull Role role) {
        roles.remove(role);
    }

    public void addAddress(@NonNull @Nonnull Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(@NonNull @Nonnull Address address) {
        addresses.remove(address);
        address.setUser(null);
    }
}
