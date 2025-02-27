package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.TimePeriod;
import com.dolgoborodovkv.sporthome.entity.enums.OfferedServiceStatus;
import com.dolgoborodovkv.sporthome.entity.users.Couch;
import com.dolgoborodovkv.sporthome.entity.users.Customer;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "offered_services")
public class OfferedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * длительность тренировки
     */
    @Column(name = "duration")
    private Duration duration;

    @Embedded
    private TimePeriod timePeriod;

    /**
     * статус предоставления услуги
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OfferedServiceStatus offeredServiceStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_id")
    private Service service;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "couch_id")
    private Couch couch;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "selected_service",
            joinColumns = @JoinColumn(name = "offered_service_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @Builder.Default
    private Set<Customer> customers = new HashSet<>();

    public void addCustomer(@NonNull @Nonnull Customer customer) {
        customers.add(customer);
        customer.getOfferedServices().add(this);
    }

    public void removeCustomer(@NonNull @Nonnull Customer customer) {
        customers.remove(customer);
        customer.getOfferedServices().remove(this);
    }
}
