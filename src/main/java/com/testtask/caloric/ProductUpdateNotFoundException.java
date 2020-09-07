package com.testtask.caloric;

class ProductUpdateNotFoundException extends RuntimeException {

    ProductUpdateNotFoundException(Long id) {
        super("Could not find product update for id = " + id);
    }
}
