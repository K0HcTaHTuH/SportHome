package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.enums.TimePeriodStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Временной интервал работы тренера, проведения тренеровок, работы зала.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Embeddable
public class TimePeriod {
    /**
     * Дата и время начала интервала.
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * Дата и время окончания интервала.
     */
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * Доступность временного интервала.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TimePeriodStatus timePeriodStatus = TimePeriodStatus.FREE;
}




