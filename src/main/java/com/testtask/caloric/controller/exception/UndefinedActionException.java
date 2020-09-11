package com.testtask.caloric.controller.exception;

/**
 * Исключение выбрасываемое при попытке вызвать недопустимое действие над сущностью
 */
public class UndefinedActionException extends RuntimeException{
    public UndefinedActionException(String message) {
        super(message);
    }
}
