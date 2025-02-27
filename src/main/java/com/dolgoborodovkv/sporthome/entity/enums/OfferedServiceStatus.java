package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OfferedServiceStatus {
    OPEN("service is provided"),
    CLOSED("service not provided");
    private final String description;
}
