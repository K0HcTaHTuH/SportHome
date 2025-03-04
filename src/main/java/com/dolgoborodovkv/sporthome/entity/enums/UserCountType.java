package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип тренировки по количеству участников
 */
@RequiredArgsConstructor
@Getter
public enum UserCountType {
    /**
     * Индивидуальная тренеровка.
     */
    INDIVIDUAL("individual training"),

    /**
     * Тренеровка с ограниченным количеством клиентов.
     */
    GROUP("group training"),

    /**
     * Тренеровка без ограничения по количеству клиентов.
     */
    UNLIMITED("training without limit of participants");

    private final String description;
}
