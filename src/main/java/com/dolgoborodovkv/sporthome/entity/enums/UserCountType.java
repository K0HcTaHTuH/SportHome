package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Тип тренировки по количеству участников
 */
@RequiredArgsConstructor
@Getter
public enum UserCountType {
    INDIVIDUAL("individual training"),
    GROUP("group training"),
    UNLIMITED("training without limit of participants");
    private final String description;
}
