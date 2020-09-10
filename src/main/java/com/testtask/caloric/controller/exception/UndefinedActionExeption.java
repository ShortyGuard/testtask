package com.testtask.caloric.controller.exception;

public class UndefinedActionExeption extends RuntimeException{
    public UndefinedActionExeption(String message) {
        super(message);
    }
}
