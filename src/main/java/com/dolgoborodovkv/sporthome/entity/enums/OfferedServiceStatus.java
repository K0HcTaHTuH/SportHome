package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Статус предоставляемой услуги.
 */
@RequiredArgsConstructor
@Getter
public enum OfferedServiceStatus {
    /**
     * Предоставляемая услуга открыта.
     */
    OPEN("service is provided"),

    /**
     * Предоставляемая услуга закрыта.
     */
    CLOSED("service not provided");
    private final String description;
}
