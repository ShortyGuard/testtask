package com.testtask.caloric.controller.exception;

/**
 * Исключение выбрасываемое при отрицательном поиске сущности по идентификатору
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Could not find entity for id = " + id);
    }
}
