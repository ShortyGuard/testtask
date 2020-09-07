package com.testtask.caloric;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class Product {

    /**
     * Сущность продукта (мапится на отдельную таблицу product
     */
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String manufacturer;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    // флаг доступности в выборке пользователям
    private boolean isAviable = false;

    public Product() {
    }

    public Product(String name, String manufacturer, double calories, double proteins, double fats, double carbohydrates, boolean isAviable) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.isAviable = isAviable;
    }
}