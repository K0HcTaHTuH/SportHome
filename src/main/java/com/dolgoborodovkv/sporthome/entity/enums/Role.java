package com.dolgoborodovkv.sporthome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Роли пользователя: новый пользователь вводит данные при регистрации
 */
@RequiredArgsConstructor
@Getter
public enum Role {
    /**
     * пользователь который ввел данные для регистрации но еще не подтвердил емаил или телефон
     */
    REGISTERED,
    /**
     * пользователь с подтвержденным емаил или телефон
     */
    CUSTOMER,
    /**
     * пользователь который ввел данные о тренерсчкой деятельности
     */
    COUCH,
    /**
     * пользователь который ввел данные о своем зале
     */
    OWNER,
    /**
     * пользователь администратор
     */
    ADMIN
}
