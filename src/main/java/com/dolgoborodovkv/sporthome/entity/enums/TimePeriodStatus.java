package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус временного периода для проведения тренеровки.
 */
@RequiredArgsConstructor
@Getter
public enum TimePeriodStatus {
    /**
     * Свободно.
     */
    FREE,

    /**
     * Забронированно.
     */
    BOOKED
}
