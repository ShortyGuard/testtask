package com.testtask.caloric.controller;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Could not find entity for id = " + id);
    }
}
