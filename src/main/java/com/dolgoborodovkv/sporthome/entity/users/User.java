package com.dolgoborodovkv.sporthome.entity.users;

import com.dolgoborodovkv.sporthome.entity.Address;
import com.dolgoborodovkv.sporthome.entity.enums.Role;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Основной класс для работы с пользователями приложения.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"addresses", "roles"})
@EqualsAndHashCode(exclude = {"addresses", "roles"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Пароль пользователя.
     */
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Дата рождения пользователя.
     */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * Пол пользователя.
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Email пользователя.
     */
    @Column(name = "email")
    private String email;

    /**
     * Телефон пользователя.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Обьект расширенного класса для работы с пользователем в роли "Coach"
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Coach coach;

    /**
     * Обьект расширенного класса для работы с пользователем в роли "Customer"
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Customer customer;

    /**
     * Обьект расширенного класса для работы с пользователем в роли "Owner"
     */
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Owner owner;

    /**
     * Адрес места проживания или пребывания пользователя, по которому можно выбрать место посещения тренеровок.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    Set<Address> addresses = new HashSet<>();

    /**
     * Список ролей доступных пользователю для выбора.
     */
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
