package com.testtask.caloric;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String manufacturer;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;

    public Product() {
    }

    public Product(String name, String manufacturer, double calories, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}