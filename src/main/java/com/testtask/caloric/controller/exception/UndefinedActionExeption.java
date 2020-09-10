package com.testtask.caloric.controller;

public class UndefinedActionExeption extends RuntimeException{
    public UndefinedActionExeption(String message) {
        super(message);
    }
}
