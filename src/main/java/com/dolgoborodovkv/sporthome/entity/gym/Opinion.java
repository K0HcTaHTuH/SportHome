package com.dolgoborodovkv.sporthome.entity.gym;

import com.dolgoborodovkv.sporthome.entity.users.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Сущность для работы и хранения отзывов оставленных клиентами.
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "opinions")
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Дата и время оставленного отзыва.
     */
    @Column(name = "post_date")
    private LocalDateTime postDate;

    /**
     * Коментарий оставленного отзыва.
     */
    @Column(name = "comment")
    private String comment;

    /**
     * Рейтинг оставленного отзыва.
     */
    @Column(name = "rating")
    private Integer rating;

    /**
     * Автор оставленного отзыва.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer author;

    /**
     * Зал которому оставлен отзыв.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;
}
