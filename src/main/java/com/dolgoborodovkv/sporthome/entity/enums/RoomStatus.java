package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус доступности комнаты для оказания услуги.
 */
@RequiredArgsConstructor
@Getter
public enum RoomStatus {
    /**
     * Комната доступна.
     */
    FREE,

    /**
     * Комната не позволяет оказать услугу.
     */
    BOOKED
}
