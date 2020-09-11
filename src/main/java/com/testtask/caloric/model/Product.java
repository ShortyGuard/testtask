package com.testtask.caloric.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * Сущность продукта (мапится на отдельную таблицу product)
 */
@Data
@Entity
@Table(name = "product")
public
class Product {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Column(name = "manufacturer")
    @NotBlank(message = "manufacturer cannot be blank")
    private String manufacturer;

    @Column(name = "calories")
    @PositiveOrZero(message = "calories must be greater than or equal to 0")
    private double calories;

    @Column(name = "proteins")
    @PositiveOrZero(message = "proteins must be greater than or equal to 0")
    private double proteins;

    @Column(name = "fats")
    @PositiveOrZero(message = "fats must be greater than or equal to 0")
    private double fats;

    @Column(name = "carbohydrates")
    @PositiveOrZero(message = "carbohydrates must be greater than or equal to 0")
    private double carbohydrates;

    // флаг доступности в выборке пользователям
    @Column(name = "is_aviable")
    private boolean isAviable = false;

    public Product() {
    }
}