package com.testtask.caloric;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ProductUpdateNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProductUpdateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductUpdateNotFoundException ex) {
        return ex.getMessage();
    }
}