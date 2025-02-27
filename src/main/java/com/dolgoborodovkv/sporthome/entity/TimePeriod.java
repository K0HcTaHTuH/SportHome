package com.dolgoborodovkv.sporthome.entity;

import com.dolgoborodovkv.sporthome.entity.enums.TimePeriodStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

/**
 * График работы тренера, график проведения тренеровок, режим работы зала.
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
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TimePeriodStatus timePeriodStatus = TimePeriodStatus.FREE;
}




