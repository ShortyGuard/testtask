package com.testtask.caloric;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
class Product {

    /**
     * Сущность продукта (мапится на отдельную таблицу product
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "calories")
    private double calories;

    @Column(name = "proteins")
    private double proteins;

    @Column(name = "fats")
    private double fats;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    // флаг доступности в выборке пользователям
    @Column(name = "is_aviable")
    private boolean isAviable = false;

    public Product() {
    }
}